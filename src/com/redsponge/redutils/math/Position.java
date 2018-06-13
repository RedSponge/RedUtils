package com.redsponge.redutils.math;

public class Position {

    public double x, y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position position) {
        this(position.x, position.y);
    }

    public Position set(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Position set(Position pos) {
        this.x = pos.x;
        this.y = pos.y;
        return this;
    }

    public Position sub(double x, double y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Position mult(double x, double y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    public Position mult(double mult) {
        return mult(mult, mult);
    }

    public Position div(double x, double y) {
        this.x /= x;
        this.y /= y;
        return this;
    }

    public Position div(double div) {
        return div(div, div);
    }



}
