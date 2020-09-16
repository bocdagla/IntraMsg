package com.example.tomeu.intramsg.utils;

import com.example.tomeu.intramsg.databaseObjects.Departament;
import com.example.tomeu.intramsg.databaseObjects.Empleat;
import com.example.tomeu.intramsg.databaseObjects.Log;
import com.example.tomeu.intramsg.databaseObjects.Missatge;
import com.example.tomeu.intramsg.databaseObjects.Pertany;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Tomeu on 08/03/2017.
 */

public class DBClient {


    public static  ArrayList<Missatge> selectAllMissatges(int idUsuari){
        ArrayList<Missatge> resultat = new ArrayList<>(
                Select.from(Missatge.class)
                        .where(
                                Condition.prop("emplorigen").eq(Integer.toString(idUsuari))
                        ).or(
                                Condition.prop("empldesti").eq(Integer.toString(idUsuari))
                        ).orderBy("idmiss").list()
        );
        return resultat;
    }

    public static Log selectLastLog() {
        return Log.last(Log.class);
    }

    public static Missatge selectLastMissatge() {
        return Missatge.last(Missatge.class);
    }

    public static Empleat getEmpleat(int id){
        return Select.from(Empleat.class)
                .where(
                        Condition.prop("idempl").eq(id)
                ).first();
    }

    public static List<Empleat> getRestaEmpleats(int id) {
        return Select.from(Empleat.class)
                .where(
                        Condition.prop("idempl").notEq(id)
                ).list();
    }

    public static Missatge getMissatge(int id) {
        return Select.from(Missatge.class)
                .where(
                        Condition.prop("idmiss").eq(id)
                ).first();
    }

    public static SugarRecord getRecordFromLog(Log log){
        SugarRecord record = null;
        switch (log.getTaula()){
            case "departament":
                record = Select.from(Departament.class)
                        .where(
                                Condition.prop("iddep").eq(Integer.toString(log.getId1()))
                        ).first();
                break;
            case "empleat":
                record = Select.from(Empleat.class)
                        .where(
                                Condition.prop("idempl").eq(Integer.toString(log.getId1()))
                        ).first();
                break;
            case "pertany":
                record = Select.from(Pertany.class)
                        .where(
                                Condition.prop("idempl").eq(Integer.toString(log.getId1())),
                                Condition.prop("iddep").eq(Integer.toString(log.getId2()))
                        ).first();

                break;
            case "missatge":
                record = Select.from(Missatge.class)
                        .where(
                                Condition.prop("idmiss").eq(Integer.toString(log.getId1()))
                        ).first();
                break;
        }
        return record;
    }

}
