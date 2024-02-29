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
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Order(1)
public class ApplicationFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;

    public ApplicationFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Intercept and modify the JSON response
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        filterChain.doFilter(request, responseWrapper);

        if (responseWrapper.getContentType() != null && responseWrapper.getContentType().startsWith(MediaType.APPLICATION_JSON_VALUE)) {
            try {
                // Récupération du contenu en byteArray
                byte[] responseArray = responseWrapper.getContentAsByteArray();

                // Parse JSON
                JsonNode root = objectMapper.readTree(new String(responseArray, StandardCharsets.UTF_8));

                // Vérification de l'existance du node _embedded && s'il contient un node enfant
                JsonNode embeddedNode = root.get("_embedded");
                if (embeddedNode != null && embeddedNode.isObject() && embeddedNode.size() == 1) {
                    JsonNode subNode = embeddedNode.elements().next(); // Récupération node enfant
                    if (subNode != null && subNode.isArray()) {
                        ArrayNode subNodeArr = (ArrayNode) subNode;

                        // Correction de la profondeur
                        ((ObjectNode) root).remove("_embedded");
                        ((ObjectNode) root).set("_embedded", subNodeArr);
                    }
                }

                // Retour du JSON dans l'objectMapper en String
                String modifiedResponseBody = objectMapper.writeValueAsString(root);

                // Ecriture du JSON dans la réponse
                response.setCharacterEncoding("UTF-8");
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.getOutputStream().write(modifiedResponseBody.getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) { System.err.println(e.getMessage()); }
        }
    }

}