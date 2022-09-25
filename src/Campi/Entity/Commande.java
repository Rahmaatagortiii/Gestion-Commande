/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Campi.Entity;

import java.time.LocalDateTime;

public class Commande {
    private int id;
    private int num_cmd	;
    private LocalDateTime date_cmd ;
    private float total_cmd ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_cmd() {
        return num_cmd;
    }

    public void setNum_cmd(int num_cmd) {
        this.num_cmd = num_cmd;
    }

    public LocalDateTime getDate_cmd() {
        return date_cmd;
    }

    public void setDate_cmd(LocalDateTime date_cmd) {
        this.date_cmd = date_cmd;
    }

    public float getTotal_cmd() {
        return total_cmd;
    }

    public void setTotal_cmd(float total_cmd) {
        this.total_cmd = total_cmd;
    }

    public Commande() {
    }

    public Commande(int id, int num_cmd, LocalDateTime date_cmd, float total_cmd) {
        this.id = id;
        this.num_cmd = num_cmd;
        this.date_cmd = date_cmd;
        this.total_cmd = total_cmd;
    }

    public Commande(int num_cmd, LocalDateTime date_cmd, float total_cmd) {
        this.num_cmd = num_cmd;
        this.date_cmd = date_cmd;
        this.total_cmd = total_cmd;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", num_cmd=" + num_cmd + ", date_cmd=" + date_cmd + ", total_cmd=" + total_cmd + '}';
    }

    
    
}
