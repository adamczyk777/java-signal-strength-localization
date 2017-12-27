package sample.models;

import sample.models.objects.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StageObjects {
    private MotherRobot motherRobot;
    private List<Robot> robots;
    private List<Antenna> antennas;

    public List<Antenna> getAntennas() { return this.antennas; }
    public List<Robot> getRobots() { return this.robots; }

    public StageObjects(Configuration config) {

        System.out.println("TEST");

        // Generating randomly located antennas
        this.antennas = new ArrayList<>(
                Arrays.asList(
                        new Antenna(new Location(config.stageWidth, config.stageHeight), config.a, config.n),
                        new Antenna(new Location(config.stageWidth, config.stageHeight), config.a, config.n),
                        new Antenna(new Location(config.stageWidth, config.stageHeight), config.a, config.n)
                )
        );

        // Generating randomly located i robots
        // ArrayList for all of the robots
        this.robots = new ArrayList<>();
        for (int i = 0; i < config.robotsCount; i++) {

            // random location
            // TODO: Refactor and make a method in Location class for generating random x and y
            Location location = new Location(config.stageWidth, config.stageHeight);

            List<Double> strengths = new ArrayList<>();
            for (Antenna antenna : antennas) {
                strengths.add(antenna.calculateStrength(location.getX(), location.getY()));
            }

            this.robots.add(new Robot(location, strengths));
        }

        // Generating randomly located mother robot
        Location location = new Location(config.stageWidth, config.stageHeight);

        List<Double> strengths = new ArrayList<>();
        for (Antenna antenna : antennas) {
            strengths.add(antenna.calculateStrength(location.getX(), location.getY()));
        }

        this.motherRobot = new MotherRobot(location, strengths);
    }
}
