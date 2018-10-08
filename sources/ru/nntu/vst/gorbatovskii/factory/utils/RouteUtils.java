package ru.nntu.vst.gorbatovskii.factory.utils;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;
import ru.nntu.vst.gorbatovskii.factory.model.common.Location;
import ru.nntu.vst.gorbatovskii.factory.model.common.Route;

import java.util.Map;

public class RouteUtils {

    private static RouteUtils routeUtils;

    public static RouteUtils getInstance() {
        if (routeUtils == null) {
            routeUtils = new RouteUtils();
        }

        return routeUtils;
    }

    private DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraShortestPath;

    private RouteUtils() {
        SimpleGraph<String, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        Map<String, Route> routes = ApplicationContextUtils.getApplicationContext().getBeansOfType(Route.class);
        if (!routes.isEmpty()) {
            for (Route route : routes.values()) {
                String locationAId = route.getPointA().getLocationId();
                String locationBId = route.getPointB().getLocationId();
                graph.addVertex(locationAId);
                graph.addVertex(locationBId);
                DefaultWeightedEdge edge = new DefaultWeightedEdge();
                graph.addEdge(locationAId, locationBId, edge);
                graph.setEdgeWeight(edge, route.getWeight());
            }
        }

        dijkstraShortestPath = new DijkstraShortestPath<>(graph);
    }

    public double getRouteLength(Location l1, Location l2) {
        return dijkstraShortestPath.getPath(l1.getLocationId(), l2.getLocationId()).getWeight();
    }
}
