package fr.iut.sciencequest.sae.controllers;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class Controller {
    protected final int PAGE_SIZE = 1;

    protected <T> EntityModel<T> getSelfLinkEntityModel(T entity, String method, Object... args) {
        Class<?>[] argTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass();
        }

        try {
            Link selfLink = linkTo(this.getClass(), this.getClass().getMethod(method, argTypes), args).withSelfRel();
            return EntityModel.of(entity, selfLink);
        } catch (NoSuchMethodException e) {
            System.err.println("Method doesn't exist");
            return null;
        }
    }

    protected <T> CollectionModel<T> getSelfLinkCollectionModel(Iterable<T> entities, String method, Object... args) {
        Class<?>[] argTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass();
        }

        try {
            Link selfLink = linkTo(this.getClass(), this.getClass().getMethod(method, argTypes), args).withSelfRel();
            return CollectionModel.of(entities, selfLink);
        } catch (NoSuchMethodException e) {
            System.err.println("Method doesn't exist");
            return null;
        }
    }


    protected <T> CollectionModel<EntityModel<T>> getPageableCollectionModel(Page<T> pagedResult, Integer page, String method, Object... args) {
        try {
            List<EntityModel<T>> entities = pagedResult.map(EntityModel::of).toList();

            Class<?>[] argTypes = new Class[args.length+1];
            for (int i = 0; i < args.length; i++) {
                argTypes[i] = args[i].getClass();
            }
            argTypes[args.length] = Optional.class;

            Method finalMethod = this.getClass().getMethod(method, argTypes);

            List<Object> selfObj = new ArrayList<>(List.of(args)); selfObj.add(page);
            Link selfLink = linkTo(this.getClass(), finalMethod, selfObj.toArray()).withSelfRel().expand(page);

            CollectionModel<EntityModel<T>> result = CollectionModel.of(entities, selfLink);

            if (pagedResult.hasPrevious()) {
                List<Object> previousObj = new ArrayList<>(List.of(args)); previousObj.add(pagedResult.previousPageable().getPageNumber());
                result.add(linkTo(this.getClass(), finalMethod, previousObj.toArray()).withRel("previous"));
            }

            if (pagedResult.hasNext()) {
                List<Object> nextObj = new ArrayList<>(List.of(args)); nextObj.add(pagedResult.nextPageable().getPageNumber());
                result.add(linkTo(this.getClass(), finalMethod, nextObj.toArray()).withRel("next"));
            }

            return result;
        } catch(NoSuchMethodException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
