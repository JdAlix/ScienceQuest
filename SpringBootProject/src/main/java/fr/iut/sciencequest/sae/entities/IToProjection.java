package fr.iut.sciencequest.sae.entities;

public interface IToProjection {
    <T> T toProjection(Class<T> projectionType);
}
