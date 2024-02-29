package fr.iut.sciencequest.sae.entities;

import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

public abstract class BaseEntity implements IToProjection {
    public <T> T toProjection(Class<T> projectionType){
        ProjectionFactory pf = new SpelAwareProxyProjectionFactory();
        return pf.createProjection(projectionType, this);
    }

}
