package fr.iut.sciencequest.sae.entities;

public interface IToProjection {
    public <T> T toProjection(Class<T> projectionType);
}
