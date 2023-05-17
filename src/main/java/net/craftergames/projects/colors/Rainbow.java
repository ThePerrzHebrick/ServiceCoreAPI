package net.craftergames.projects.colors;

import net.craftergames.projects.colors.exceptions.HomogeneousRainbowException;
import net.craftergames.projects.colors.exceptions.InvalidColourException;
import net.craftergames.projects.colors.exceptions.NumberRangeException;

import java.util.ArrayList;

public class Rainbow {

    private double minNum;
    private double maxNum;
    private String[] colours;
    private ArrayList<ColourGradient> colourGradients;

    public Rainbow() {
        try {
            minNum = 0;
            maxNum = 100;
            colours = new String[]{"red", "yellow", "lime", "blue"};
            setSpectrum(colours);
        } catch (InvalidColourException | HomogeneousRainbowException | NumberRangeException e) {
            throw new AssertionError(e);
        }

    }

    public String colourAt(double number) {
        if (colourGradients.size() == 1) {
            return colourGradients.get(0).colourAt(number);
        } else {
            double segment = (maxNum - minNum) / (colourGradients.size());
            int index = (int) Math.min(Math.floor((Math.max(number, minNum) - minNum) / segment), colourGradients.size() - 1);
            return colourGradients.get(index).colourAt(number);
        }
    }


    public void setSpectrum(String... spectrum) throws HomogeneousRainbowException, InvalidColourException, NumberRangeException {
        if (spectrum.length < 2) {
            throw new HomogeneousRainbowException();
        } else {
            double increment = (maxNum - minNum) / (spectrum.length - 1);
            ColourGradient firstGradient = new ColourGradient();
            firstGradient.setGradient(spectrum[0], spectrum[1]);
            firstGradient.setNumberRange(minNum, minNum + increment);

            colourGradients = new ArrayList<ColourGradient>();
            colourGradients.add(firstGradient);

            for (int i = 1; i < spectrum.length - 1; i++) {
                ColourGradient colourGradient = new ColourGradient();
                colourGradient.setGradient(spectrum[i], spectrum[i + 1]);
                colourGradient.setNumberRange(minNum + increment * i, minNum + increment * (i + 1));
                colourGradients.add(colourGradient);
            }

            colours = spectrum;
        }
    }


    public void setNumberRange(double minNumber, double maxNumber) throws NumberRangeException {
        try {
            if (maxNumber > minNumber) {
                minNum = minNumber;
                maxNum = maxNumber;
                setSpectrum(colours);
            } else {
                throw new NumberRangeException(minNumber, maxNumber);
            }
        } catch (HomogeneousRainbowException e) {
            throw new RuntimeException(e);
        } catch (InvalidColourException e) {
            throw new RuntimeException(e);
        }
    }

    public String colorAt(double number) {
        return colourAt(number);
    }

}

