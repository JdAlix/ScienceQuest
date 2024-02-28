package fr.iut.sciencequest.sae;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
@Order(1)
public class ApplicationFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;

    public ApplicationFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Intercept and modify the JSON response
        JsonInterceptingResponseWrapper responseWrapper = new JsonInterceptingResponseWrapper(response);

        filterChain.doFilter(request, responseWrapper);

        // Check if the response content type is JSON
        if (responseWrapper.getContentType() != null && responseWrapper.getContentType().startsWith(MediaType.APPLICATION_JSON_VALUE)) {
            try {
                // Parse the JSON response
                JsonNode root = objectMapper.readTree(responseWrapper.getContent());

                // Check if the _embedded node exists and if it contains the tupleBackedMapList node
                JsonNode embeddedNode = root.get("_embedded");
                if (embeddedNode != null && embeddedNode.isObject()) {
                    JsonNode tupleBackedMapListNode = embeddedNode.get("tupleBackedMapList");
                    if (tupleBackedMapListNode != null && tupleBackedMapListNode.isArray()) {
                        ArrayNode tupleBackedMapList = (ArrayNode) tupleBackedMapListNode;

                        // Remove the _embedded node
                        ((ObjectNode) root).remove("_embedded");

                        // Set the tupleBackedMapList directly to the root
                        ((ObjectNode) root).set("_embedded", tupleBackedMapList);
                    }
                }

                // Convert the modified JSON back to string
                String modifiedResponseBody = objectMapper.writeValueAsString(root);

                // Write the modified JSON back to the response
                response.getOutputStream().write(modifiedResponseBody.getBytes());
            } catch (IOException e) {
                // Handle exception
                e.printStackTrace();
            }
        }
    }

    private static class JsonInterceptingResponseWrapper extends HttpServletResponseWrapper {
        private ByteArrayOutputStream baos = new ByteArrayOutputStream();

        public JsonInterceptingResponseWrapper(HttpServletResponse response) {
            super(response);
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            return new ServletOutputStreamWrapper(baos);
        }

        public String getContent() {
            return baos.toString();
        }
    }

    private static class ServletOutputStreamWrapper extends ServletOutputStream {
        private ByteArrayOutputStream baos;

        public ServletOutputStreamWrapper(ByteArrayOutputStream baos) {
            this.baos = baos;
        }

        @Override
        public void write(int b) throws IOException {
            baos.write(b);
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {
            throw new UnsupportedOperationException("Not implemented");
        }
    }

}