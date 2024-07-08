/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.integrador.util;

import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author Top System
 */
public class CombinedFilter extends DocumentFilter {
    
    private final int limite;
    private final boolean useUpperCase;

    public CombinedFilter(int limite, boolean useUpperCase) {
        this.limite = limite;
        this.useUpperCase = useUpperCase;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (useUpperCase) {
            string = string.toUpperCase();
        } else {
            string = string.toLowerCase();
        }

        if ((fb.getDocument().getLength() + string.length()) <= limite) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (useUpperCase) {
            text = text.toUpperCase();
        } else {
            text = text.toLowerCase();
        }

        if ((fb.getDocument().getLength() + text.length() - length) <= limite) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    public static void applyUpperCaseFilter(AbstractDocument document, int limite) {
        document.setDocumentFilter(new CombinedFilter(limite, true));
    }

    public static void applyLowerCaseFilter(AbstractDocument document, int limite) {
        document.setDocumentFilter(new CombinedFilter(limite, false));
    }
}

