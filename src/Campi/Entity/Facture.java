/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Campi.Entity;

import java.time.LocalDateTime;

public class Facture {
    private int id;
    private int num_fact ;
    private float total_fact ;
    private LocalDateTime date_fact;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_fact() {
        return num_fact;
    }

    public void setNum_fact(int num_fact) {
        this.num_fact = num_fact;
    }

    public float getTotal_fact() {
        return total_fact;
    }

    public void setTotal_fact(float total_fact) {
        this.total_fact = total_fact;
    }

    public LocalDateTime getDate_fact() {
        return date_fact;
    }

    public void setDate_fact(LocalDateTime date_fact) {
        this.date_fact = date_fact;
    }

    public Facture() {
    }

    public Facture(int id, int num_fact, float total_fact, LocalDateTime date_fact) {
        this.id = id;
        this.num_fact = num_fact;
        this.total_fact = total_fact;
        this.date_fact = date_fact;
    }

    public Facture(int num_fact, float total_fact, LocalDateTime date_fact) {
        this.num_fact = num_fact;
        this.total_fact = total_fact;
        this.date_fact = date_fact;
    }

    @Override
    public String toString() {
        return "Facture{" + "id=" + id + ", num_fact=" + num_fact + ", total_fact=" + total_fact + ", date_fact=" + date_fact + '}';
    }

   


    
}
