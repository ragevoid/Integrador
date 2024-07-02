/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.integrador.model;

import com.toedter.calendar.IDateEvaluator;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HighlightEvaluator implements IDateEvaluator {

    private final List<Date> list = new ArrayList<>();

    public void add(Date date) {
        list.add(date);
    }

    public void addAll(List<Date> dates) {
        list.addAll(dates);
    }

    @Override
    public boolean isSpecial(Date date) {
        return list.contains(date);
    }

    @Override
    public Color getSpecialForegroundColor() {
        return Color.red.darker();
    }

    @Override
    public Color getSpecialBackroundColor() {
        return Color.blue;
    }

    @Override
    public String getSpecialTooltip() {
        return "Highlighted event.";
    }

    @Override
    public boolean isInvalid(Date date) {
        return false;
    }

    @Override
    public Color getInvalidForegroundColor() {
        return null;
    }

    @Override
    public Color getInvalidBackroundColor() {
        return null;
    }

    @Override
    public String getInvalidTooltip() {
        return null;
    }
}
