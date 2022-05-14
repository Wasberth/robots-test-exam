/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robots;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import willy.structures.QueueArray;
import willy.structures.WLinkedList;
import willy.structures.WList;
import willy.util.WRandom;

/**
 *
 * @author Willy
 */
public class Robots {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        final Scanner sc = new Scanner(System.in);
        final WRandom r = new WRandom();

        WList<Person> robots[] = new WLinkedList[5];
        QueueArray<Person> q;

        for (int i = 0; i < 5; i++) {
            robots[i] = new WLinkedList<>();
        }

        int maxQ = 0;

        while (maxQ <= 0) {
            if (maxQ == -1) {
                System.out.println("Ese no es un valor correcto");
            }

            System.out.println("Ingrese el tamaño máximo de la cola (mayor a 0)");
            try {
                maxQ = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                maxQ = -1;
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error inesperado, terminando el programa");
                System.err.println(e.getMessage());
                System.exit(1);
            }
        }

        q = new QueueArray(Person.class, maxQ);

        String name = "";
        String ms = "";
        int height = 0;

        while (!q.isFull()) {
            if (name.equals("")) {
                System.out.println("Ingrese el nombre de la siguiente persona");
                name = sc.nextLine();
            }

            System.out.println("Ingrese su altura en centímetros");
            try {
                height = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                height = -1;
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error inesperado, terminando el programa");
                System.err.println(e.getMessage());
                System.exit(1);
            }

            if (height == -1) {
                System.out.println("Esa no es una altura");
                continue;
            }

            Person p = new Person(name, height, r.nextInt(18, 60));

            System.out.println("Se formó en la cola " + p + "\n");

            q.add(p);
            name = "";
            height = 0;
        }

        sendToRobots(q, robots);

        int turn = 1;
        int robot = -1;
        Person p1, p2;
        String watin;

        boolean emptyRobots[] = {false, false, false, false, false};
        boolean allEmpty = false;

        while (!allEmpty) {
            robot = (robot + 1) % 5;

            if (robots[robot].isEmpty()) {
                //System.out.println("Ya no hay más personas esperando en el robot " + (robot + 1));
                emptyRobots[robot] = true;
                allEmpty = checkIfAllEmpty(emptyRobots);
                continue;
            }

            p1 = robots[robot].popFirst();

            if (robots[robot].isEmpty()) {
                System.out.println("\nTurno: " + turn);
                System.out.println("El robot " + (robot + 1) + " está atendiendo a " + p1);
                System.out.println("Ya no hay más personas esperando en el robot");

                TimeUnit.SECONDS.sleep(1);
                emptyRobots[robot] = true;
                allEmpty = checkIfAllEmpty(emptyRobots);
                turn++;
                continue;
            }

            p2 = robots[robot].popFirst();

            System.out.println("\nTurno: " + turn);
            System.out.println("El robot " + (robot + 1) + " está atendiendo a " + p1 + " y a " + p2);

            watin = robots[robot].toString();
            watin = watin.substring(1, watin.length() - 1);
            emptyRobots[robot] = watin.equals("");
            watin = watin.equals("") ? "Ya no hay más personas esperando en el robot"
                    : ("Siguen esperando " + watin);

            System.out.println(watin);

            TimeUnit.SECONDS.sleep(1);
            allEmpty = checkIfAllEmpty(emptyRobots);
            turn++;
        }

        System.out.println("\nYa no hay nadie esperando");
    }

    private static void sendToRobots(final QueueArray<Person> q, final WList<Person>[] robots) throws InterruptedException {
        while (!q.isEmpty()) {
            int nextAge = q.peek().getAge();
            int robot
                    = nextAge > 18 && nextAge < 22 ? 0
                            : nextAge == 18 ? 1
                                    : nextAge > 22 && nextAge < 44 ? 2
                                            : nextAge == 22 || nextAge == 44 ? 3
                                                    : nextAge > 44 && nextAge <= 60 ? 4
                                                            : -1;

            if (robot == -1) {
                System.out.println("La edad no es correcta");
                q.remove();
                continue;
            }

            System.out.println("Mandando " + q.peek() + " con el robot " + (robot + 1));
            robots[robot].pushLast(q.remove());
        }

        TimeUnit.SECONDS.sleep(2);

    }

    private static boolean checkIfAllEmpty(boolean allEmpty[]) {
        boolean a = true;

        for (boolean b : allEmpty) {
            a = a && b;
        }

        return a;
    }
}
