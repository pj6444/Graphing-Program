package com.patrickfeltes.graphingprogram;

import com.patrickfeltes.graphingprogram.parser.Parser;
import com.patrickfeltes.graphingprogram.parser.Tokenizer;
import com.patrickfeltes.graphingprogram.parser.exceptions.InvalidExpressionException;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void binaryAddition() throws Exception {
        assertEquals(8, new Parser(new Tokenizer("6 + 2")).parse().evaluate(null), 0.0);
    }

    @Test
    public void binarySubtraction() throws Exception {
        assertEquals(4, new Parser(new Tokenizer("6 - 2")).parse().evaluate(null), 0.0);
    }

    @Test
    public void binaryMultiplication() throws Exception {
        assertEquals(12, new Parser(new Tokenizer("6 * 2")).parse().evaluate(null), 0.0);
    }

    @Test
    public void binaryDivision() throws Exception {
        assertEquals(3, new Parser(new Tokenizer("6 / 2")).parse().evaluate(null), 0.0);
    }

    @Test
    public void simpleParentheses() throws Exception {
        assertEquals(32, new Parser(new Tokenizer("(6 + 2) * 4")).parse().evaluate(null), 0.0);
    }

    @Test
    public void allBasicOperations() throws Exception {
        assertEquals(9, new Parser(new Tokenizer("6 + 2 * 3 / 2")).parse().evaluate(null), 0.0);
    }

    @Test
    public void nestedParentheses() throws Exception {
        assertEquals(112, new Parser(new Tokenizer("((12 + 2) * 4) * 2")).parse().evaluate(null), 0.0);
    }

    @Test
    public void leadingNegative() throws Exception {
        assertEquals(-4, new Parser(new Tokenizer("-6 + 2")).parse().evaluate(null), 0.0);
    }

    @Test
    public void basicPower() throws Exception {
        assertEquals(32, new Parser(new Tokenizer("2 ^ 5")).parse().evaluate(null), 0.0);
        assertEquals(64, new Parser(new Tokenizer("2 ^ 6")).parse().evaluate(null), 0.0);
        assertEquals(27, new Parser(new Tokenizer("3 ^ 3")).parse().evaluate(null), 0.0);
    }

    @Test
    public void nestedPowers() throws Exception {
        assertEquals(16, new Parser(new Tokenizer("2 ^ (2 ^ 2)")).parse().evaluate(null), 0.0);
        assertEquals(65536, new Parser(new Tokenizer("2 ^ (2 ^ (2 ^ 2))")).parse().evaluate(null), 0.0);
    }

    @Test
    public void otherOperationsInPowers() throws Exception {
        assertEquals(16, new Parser(new Tokenizer("2 ^ (12 - 12 + 2 * 2 / 2 * 4 / 2)")).parse().evaluate(null), 0.0);
    }

    @Test
    public void simpleVariableTest() throws Exception {
        Map<String, Double> variableMap = new HashMap<>();
        variableMap.put("x", 1.0);
        assertEquals(1, new Parser(new Tokenizer("x")).parse().evaluate(variableMap), 0.0);
    }

    @Test
    public void operationsWithVariable() throws Exception {
        Map<String, Double> variableMap = new HashMap<>();
        variableMap.put("x", 2.0);
        assertEquals(11, new Parser(new Tokenizer("2 * x + x + 2 ^ x + x / 2")).parse().evaluate(variableMap), 0.0);
    }

    @Test
    public void doublesTest() throws Exception {
        assertEquals(1.0, new Parser(new Tokenizer("0.5 * 2")).parse().evaluate(null), 0.0);
        assertEquals(0.7, new Parser(new Tokenizer("0.2 + 0.7 - 0.2")).parse().evaluate(null), 0.0);
        assertEquals(2.0, new Parser(new Tokenizer("4 ^ 0.5")).parse().evaluate(null), 0.0);
    }

    @Test
    public void testIrrationals() throws Exception {
        assertEquals(Math.PI, new Parser(new Tokenizer("pi")).parse().evaluate(null), 0.0);
        assertEquals(Math.E, new Parser(new Tokenizer("e")).parse().evaluate(null), 0.0);
    }

    @Test
    public void testTrigFunctions() throws Exception {
        assertEquals(Math.cos(Math.PI / 2), new Parser(new Tokenizer("cos(pi/2)")).parse().evaluate(null), 0.0);
        assertEquals(Math.cos(1), new Parser(new Tokenizer("cos(1)")).parse().evaluate(null), 0.0);
        assertEquals(Math.sin(Math.PI / 2), new Parser(new Tokenizer("sin(pi/2)")).parse().evaluate(null), 0.0);
        assertEquals(Math.sin(1), new Parser(new Tokenizer("sin(1)")).parse().evaluate(null), 0.0);
        assertEquals(Math.tan(Math.PI), new Parser(new Tokenizer("tan(pi)")).parse().evaluate(null), 0.0);
        assertEquals(Math.tan(1), new Parser(new Tokenizer("tan(1)")).parse().evaluate(null), 0.0);
        assertEquals(1 / Math.cos(Math.PI), new Parser(new Tokenizer("sec(pi)")).parse().evaluate(null), 0.0);
        assertEquals(1 / Math.cos(1), new Parser(new Tokenizer("sec(1)")).parse().evaluate(null), 0.0);
        assertEquals(1 / Math.sin(Math.PI / 2), new Parser(new Tokenizer("csc(pi / 2)")).parse().evaluate(null), 0.0);
        assertEquals(1 / Math.sin(1), new Parser(new Tokenizer("csc(1)")).parse().evaluate(null), 0.0);
        assertEquals(1 / Math.tan(Math.PI), new Parser(new Tokenizer("cot(pi)")).parse().evaluate(null), 0.0);
        assertEquals(1 / Math.tan(1), new Parser(new Tokenizer("cot(1)")).parse().evaluate(null), 0.0);
    }

    @Test
    public void testHyperbolicTrigFunctions() throws Exception {
        assertEquals(Math.cosh(Math.PI / 2), new Parser(new Tokenizer("cosh(pi/2)")).parse().evaluate(null), 0.0);
        assertEquals(Math.cosh(1), new Parser(new Tokenizer("cosh(1)")).parse().evaluate(null), 0.0);
        assertEquals(Math.sinh(Math.PI / 2), new Parser(new Tokenizer("sinh(pi/2)")).parse().evaluate(null), 0.0);
        assertEquals(Math.sinh(1), new Parser(new Tokenizer("sinh(1)")).parse().evaluate(null), 0.0);
        assertEquals(Math.tanh(Math.PI), new Parser(new Tokenizer("tanh(pi)")).parse().evaluate(null), 0.0);
        assertEquals(Math.tanh(1), new Parser(new Tokenizer("tanh(1)")).parse().evaluate(null), 0.0);
        assertEquals(2.0 / (Math.pow(Math.E, Math.PI) + Math.pow(Math.E, -Math.PI)), new Parser(new Tokenizer("sech(pi)")).parse().evaluate(null), 0.0);
        assertEquals(2.0 / (Math.pow(Math.E, 1) + Math.pow(Math.E, -1)), new Parser(new Tokenizer("sech(1)")).parse().evaluate(null), 0.0);
        assertEquals(2.0 / (Math.pow(Math.E, Math.PI) - Math.pow(Math.E, -Math.PI)), new Parser(new Tokenizer("csch(pi)")).parse().evaluate(null), 0.0);
        assertEquals(2.0 / (Math.pow(Math.E, 1) - Math.pow(Math.E, -1)), new Parser(new Tokenizer("csch(1)")).parse().evaluate(null), 0.0);
        assertEquals((Math.pow(Math.E, 2 * Math.PI) + 1) / (Math.pow(Math.E, 2 * Math.PI) - 1), new Parser(new Tokenizer("coth(pi)")).parse().evaluate(null), 0.0);
        assertEquals((Math.pow(Math.E, 2 * 1) + 1) / (Math.pow(Math.E, 2 * 1) - 1), new Parser(new Tokenizer("coth(1)")).parse().evaluate(null), 0.0);
    }

    @Test
    public void inverseTrigFunctions() throws Exception {
        assertEquals(Math.acos(1), new Parser(new Tokenizer("arccos(1)")).parse().evaluate(null), 0.0);
        assertEquals(Math.asin(1), new Parser(new Tokenizer("arcsin(1)")).parse().evaluate(null), 0.0);
        assertEquals(Math.atan(1), new Parser(new Tokenizer("arctan(1)")).parse().evaluate(null), 0.0);
        assertEquals(Math.acos(1 / 2.0), new Parser(new Tokenizer("arcsec(2)")).parse().evaluate(null), 0.0);
        assertEquals(Math.asin(1 / 2.0), new Parser(new Tokenizer("arccsc(2)")).parse().evaluate(null), 0.0);
        assertEquals(Math.atan(1 / 2.0), new Parser(new Tokenizer("arccot(2)")).parse().evaluate(null), 0.0);
    }

    @Test
    public void testLog() throws Exception {
        assertEquals(Double.NaN, new Parser(new Tokenizer("ln(-1)")).parse().evaluate(null), 0.0);
        assertEquals(1.0, new Parser(new Tokenizer("ln(e)")).parse().evaluate(null), 0.0);
        assertEquals(Math.log(1), new Parser(new Tokenizer("ln(1)")).parse().evaluate(null), 0.0);
    }

    @Test
    public void testSqrt() throws Exception {
        assertEquals(2.0, new Parser(new Tokenizer("sqrt(4.0)")).parse().evaluate(null), 0.0);
        assertEquals(Math.sqrt(2.3), new Parser(new Tokenizer("sqrt(2.3)")).parse().evaluate(null), 0.0);
    }

    @Test
    public void testMultipleUnary() throws Exception {
        assertEquals(4.0, new Parser(new Tokenizer("3 - -1")).parse().evaluate(null), 0.0);
        assertEquals(2.0, new Parser(new Tokenizer("3 - - - 1")).parse().evaluate(null), 0.0);
    }

    @Test(expected = InvalidExpressionException.class)
    public void tooFewParentheses() throws Exception {
        new Parser(new Tokenizer("(( 1 + 2 )")).parse().evaluate(null);
    }

    @Test(expected = InvalidExpressionException.class)
    public void tooManyParentheses() throws Exception {
        new Parser(new Tokenizer("(( 1 + 2 )))")).parse().evaluate(null);
    }
}