package sample.viewcontroller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.models.*;

import java.util.List;


public class EntryController {

//    public void drawRandomStage() {

        // Reference to the JavaFX Canvas objects
        @FXML
        private Canvas constCanvas;
        @FXML
        private Canvas activeCanvas;
        @FXML
        private Canvas passiveCanvas;
        @FXML
        private Group stageGroup;

        // New stage object along with configuration object as a constructor parameter
        Configuration config = new Configuration();
        StageObjects stage = new StageObjects(config);

        @FXML
        void initialize() {
            // Setting canvas width and height
            constCanvas.setWidth(config.stageWidth);
            activeCanvas.setWidth(config.stageWidth);
            passiveCanvas.setWidth(config.stageWidth);

            constCanvas.setHeight(config.stageWidth);
            activeCanvas.setHeight(config.stageWidth);
            passiveCanvas.setHeight(config.stageWidth);

            // Acquisition of canvas context
            GraphicsContext constCtx = constCanvas.getGraphicsContext2D();
            GraphicsContext activeCtx = activeCanvas.getGraphicsContext2D();
            GraphicsContext passiveCtx = passiveCanvas.getGraphicsContext2D();

            // Drawing grid
            drawGrid( constCtx, config.stageWidth, config.stageHeight);

            // Drawing stage content
            drawAntennas( passiveCtx, stage.getAntennas() );
            drawRobots( activeCtx, stage.getRobots() );
            drawMotherRobot( passiveCtx, stage.getMotherRobot() );

            stageGroup.setOnMouseClicked((EventHandler<Event>) event -> {
                System.out.println("TEST");
                clearCanvas(activeCtx, config.stageWidth, config.stageHeight);
            });
        }

        /**
        * Method is used for visualisation of Antennas location.
        *
        * @param ctx GraphicsContext object allowing to draw on selected canvas
        * @param antennas List<Antenna> object containing information about the location of antennas
        */
        private void drawAntennas(GraphicsContext ctx, List<Antenna> antennas) {

            ctx.setFill(Color.RED);
            ctx.setStroke(Color.RED);
            int innerRadius = 5;
            int outerRadius = 10;

            for (Antenna element : antennas) {
                ctx.fillOval(element.getLocation().getX() - innerRadius, element.getLocation().getY() - innerRadius, 2* innerRadius, 2* innerRadius);
                ctx.strokeOval(element.getLocation().getX() - outerRadius, element.getLocation().getY() - outerRadius, 2* outerRadius, 2* outerRadius);
            }

            ctx.setFill(Color.rgb(255, 0, 0, 0.2));

            ctx.fillPolygon (
                new double[] { antennas.get(0).getLocation().getX(), antennas.get(1).getLocation().getX(), antennas.get(2).getLocation().getX() },
                new double[] { antennas.get(0).getLocation().getY(), antennas.get(1).getLocation().getY(), antennas.get(2).getLocation().getY() }, 3
            );

            ctx.strokePolygon (
                new double[] { antennas.get(0).getLocation().getX(), antennas.get(1).getLocation().getX(), antennas.get(2).getLocation().getX() },
                new double[] { antennas.get(0).getLocation().getY(), antennas.get(1).getLocation().getY(), antennas.get(2).getLocation().getY() }, 3
            );
        }

        /**
        * Method is used for visualisation of Robots location.
        *
        * @param ctx GraphicsContext object allowing to draw on selected canvas
        * @param robots List<Robot> object containing information about the location of robots
        */
        private void drawRobots(GraphicsContext ctx, List<Robot> robots) {

            ctx.setFill(Color.BLUE);
            ctx.setStroke(Color.BLUE);
            int radius = 5;

            for (Robot element : robots) {
                ctx.fillOval(element.getLocation().getX() - radius, element.getLocation().getY() - radius, 2* radius, 2* radius);
            }
        }

        /**
        * Method is used for visualisation of Mother Robot location.
        *
        * @param ctx GraphicsContext object allowing to draw on selected canvas
        * @param motherRobot  MotherRobot object containing information about the location of mother robot
        */
        private void drawMotherRobot(GraphicsContext ctx, MotherRobot motherRobot) {

            ctx.setFill(Color.BLUE);
            ctx.setStroke(Color.BLUE);
            int radius = 10;

            ctx.fillOval(motherRobot.getLocation().getX() - radius, motherRobot.getLocation().getY() - radius, 2* radius, 2* radius);

        }

        /**
        * Method is used for creating background grid.
        *
        * @param ctx GraphicsContext object allowing to draw on selected canvas
        * @param stageWidth int width of canvas.
        * @param stageHeight int height of canvas.
        */
        private void drawGrid(GraphicsContext ctx, int stageWidth, int stageHeight) {
            // Spacing between lines of the grid
            int spacing = 50;

            // Set new value of line width
            ctx.setLineWidth(1);

            // Number of lines to draw horizontally and vertically
            final int hLineCount = (int) Math.floor((stageHeight + 1) / spacing);
            final int vLineCount = (int) Math.floor((stageWidth + 1) / spacing);

            ctx.setStroke(Color.LIGHTGRAY);

            for (int i = 0; i < hLineCount; i++) {
                ctx.strokeLine(0, i * spacing, stageWidth,i * spacing);
            }

            for (int i = 0; i < vLineCount; i++) {
                ctx.strokeLine(i * spacing, 0, i * spacing, stageHeight);
            }
        }

        /**
        * Method is used for clearing canvas.
        *
        * @param ctx GraphicsContext object allowing to draw on selected canvas
        * @param stageWidth int width of canvas.
        * @param stageHeight int height of canvas.
        */
        private void clearCanvas(GraphicsContext ctx, int stageWidth, int stageHeight) {
            ctx.clearRect(0, 0, stageWidth, stageHeight);
        }

//    }

}
