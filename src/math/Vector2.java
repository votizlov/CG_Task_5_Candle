/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package math;


import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Vector2 {
    private double x, y;

    /**
     * Создаёт новый вектор/точку
     * @param x x-составляющая (горизонтальная ось)
     * @param y y-составляющая (вертикальная ось)
     */
    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 pos, Vector2 location) {
        this.x = location.x-pos.x;
        this.y = location.y-pos.y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    /**
     * Вычисляет новый вектор, который является результатом сложения двух векторов
     * @param other другой вектор, с которым складывается текущий
     * @return новый вектор
     */
    public Vector2 add(Vector2 other) {
        return new Vector2(x + other.x, y + other.y);
    }
    
    /**
     * Вычисляет новый вектор, котрый является результатом умножаения текущего вектора на число
     * @param n число, на которое умножается вектор
     * @return новый вектор
     */
    public Vector2 mul(double n) {
        return new Vector2((x * n), y * n);
    }
    
    /**
     * Метод возвращает единичный (нормализованный) вектор
     * Если вектор нулевой, то возвращает новый нулевой вектор
     * @return Получившийся вектор
     */
    public Vector2 normolized() {
        double len = length();
        if (len < 1e-12) /*Да, здесь не по модулю, т.к. корень не будет отрицательным.*/
            return new Vector2(0, 0);
        return new Vector2(x / len, y / len);
    }
    
    /**
     * Метод возвращает модуль вектора
     * @return Вычесленное значение
     */
    public double length() {
        return sqrt(x*x + y*y);
    }

    public double getLengthBetweenPoints(Vector2 secondP){
        return sqrt(pow((secondP.getX()-x),2) + pow((secondP.getY()-y),2));
    }
}
