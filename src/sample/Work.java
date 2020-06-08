package sample;

import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

class Thread1 extends Thread{
    private final Work work = new Work();
    private final Statement statement;
    private final int count;
    private final int switcher;
    private ArrayList<Dot> dots = new ArrayList<>();
    Thread1(Statement statement, int count, int switcher){
        this.statement = statement;
        this.count = count;
        this.switcher = switcher;
    }
    @Override
    public void run() {
        try{
            if(switcher == 1){
                System.out.println("without");
                dots = work.test1Thread(statement, count);

            }else if(switcher == 2){
                System.out.println("prepare");
                dots = work.test1ThreadWithPrepare(statement, count);
            }else if(switcher == 3){
                System.out.println("index");
                dots = work.test1ThreadWithIndex(statement, count);
            }else if (switcher == 4){
                System.out.println("all");
                dots = work.test1ThreadWithAll(statement, count);
            }else{
                System.out.println("Incorrect data");
            }
        }catch (SQLException | InterruptedException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Dot> getDots() { return dots; }
}

class Thread2 extends Thread{
    private final Work work = new Work();
    private final int count;
    private final int switcher;
    private ArrayList<Dot> dots = new ArrayList<>();
    DBStarter db = new DBStarter();
    Queries q = new Queries();
    Thread2(int count, int switcher) throws SQLException {
        this.count = count;
        this.switcher = switcher;
    }
    Connection connection = db.getConnection();
    Statement statement = connection.createStatement();
    @Override
    public void run() {
        try{
            if(switcher == 1){
                for(int i = 1; i <= 1; i++){
                    double averageTime = 0.0;
                    long startTime = System.currentTimeMillis();
                    for(int j = 0; j < i*count; j++){
                        ResultSet rs1 = statement.executeQuery("explain analyze " + q.getQuery1("'SASU%'"));
                        averageTime += work.explainParser(rs1);
                        ResultSet rs2 = statement.executeQuery("explain analyze " + q.getQuery2(2));
                        averageTime += work.explainParser(rs2);
                        ResultSet rs3 = statement.executeQuery("explain analyze " + q.getQuery3(100));
                        averageTime += work.explainParser(rs3);
                        ResultSet rs4 = statement.executeQuery("explain analyze " + q.getQuery4(1));
                        averageTime += work.explainParser(rs4);
                        ResultSet rs5 = statement.executeQuery("explain analyze " + q.getQuery5(1));
                        averageTime += work.explainParser(rs5);
                    }
                    long endTime = System.currentTimeMillis();
                    double time = (double) endTime-startTime;
                    time = time/(i*count*5);
                    averageTime = averageTime / (i*count*5);
                    System.out.println(averageTime + " " + i + " " + (endTime-startTime) + " average time " + time);
                    dots.add(new Dot(i, time));
                }
            }else if(switcher == 2){
                statement.execute("prepare query1(varchar) as " + q.getPreparedQuery1());
                statement.execute("prepare query2(int) as " + q.getPreparedQuery2());
                statement.execute("prepare query3(int) as " + q.getPreparedQuery3());
                statement.execute("prepare query4(int) as " + q.getPreparedQuery4());
                statement.execute("prepare query5(int) as " + q.getPreparedQuery5());
                for(int i = 1; i <= 1; i++){
                    double averageTime = 0.0;
                    long startTime = System.currentTimeMillis();
                    for(int j = 0; j < i*count; j++){
                        ResultSet rs1 = statement.executeQuery("explain analyze execute query1(100);");
                        averageTime += work.explainParser(rs1);
                        ResultSet rs2 = statement.executeQuery("explain analyze execute query2(2);");
                        averageTime += work.explainParser(rs2);
                        ResultSet rs3 = statement.executeQuery("explain analyze execute query3(100);");
                        averageTime += work.explainParser(rs3);
                        ResultSet rs4 = statement.executeQuery("explain analyze execute query4(1);");
                        averageTime += work.explainParser(rs4);
                        ResultSet rs5 = statement.executeQuery("explain analyze execute query5(1);");
                        averageTime += work.explainParser(rs5);
                    }
                    long endTime = System.currentTimeMillis();
                    double time =  endTime-startTime;
                    time = time/(i*count*5);
                    averageTime = averageTime / (i*count*5);
                    System.out.println(averageTime + " " + i + " " + (endTime-startTime) + " average time " + time);
                    dots.add(new Dot(i, time));
                }
            }else if(switcher == 3){
                //work.indexDropper(statement);
                //work.indexSetter(statement);
                for(int i = 1; i <= 1; i++){
                    double averageTime = 0.0;
                    long startTime = System.currentTimeMillis();
                    for(int j = 0; j < i*count; j++){
                        ResultSet rs1 = statement.executeQuery("explain analyze " + q.getQuery1("'SASU%'"));
                        averageTime += work.explainParser(rs1);
                        ResultSet rs2 = statement.executeQuery("explain analyze " + q.getQuery2(2));
                        averageTime += work.explainParser(rs2);
                        ResultSet rs3 = statement.executeQuery("explain analyze " + q.getQuery3(100));
                        averageTime += work.explainParser(rs3);
                        ResultSet rs4 = statement.executeQuery("explain analyze " + q.getQuery4(1));
                        averageTime += work.explainParser(rs4);
                        ResultSet rs5 = statement.executeQuery("explain analyze " + q.getQuery5(1));
                        averageTime += work.explainParser(rs5);
                    }
                    long endTime = System.currentTimeMillis();
                    double time = (double) endTime-startTime;
                    time = time/(i*count*6);
                    averageTime = averageTime / (i*count*5);
                    System.out.println(averageTime + " " + i + " " + (endTime-startTime) + " average time " + time);
                    dots.add(new Dot(i, time));
                }
                //work.indexDropper(statement);
            }else if (switcher == 4){
                //work.indexDropper(statement);
                //work.indexSetter(statement);
                statement.execute("prepare queryy1(varchar) as " + q.getPreparedQuery1());
                statement.execute("prepare queryy2(int) as " + q.getPreparedQuery2());
                statement.execute("prepare queryy3(int) as " + q.getPreparedQuery3());
                statement.execute("prepare queryy4(int) as " + q.getPreparedQuery4());
                statement.execute("prepare queryy5(int) as " + q.getPreparedQuery5());

                for(int i = 1; i <= 1; i++){
                    double averageTime = 0.0;
                    long startTime = System.currentTimeMillis();
                    for(int j = 0; j < i*count; j++){
                        ResultSet rs1 = statement.executeQuery("explain analyze execute queryy1(100);");
                        averageTime += work.explainParser(rs1);
                        ResultSet rs2 = statement.executeQuery("explain analyze execute queryy2(2);");
                        averageTime += work.explainParser(rs2);
                        ResultSet rs3 = statement.executeQuery("explain analyze execute queryy3(100);");
                        averageTime += work.explainParser(rs3);
                        ResultSet rs4 = statement.executeQuery("explain analyze execute queryy4(1);");
                        averageTime += work.explainParser(rs4);
                        ResultSet rs5 = statement.executeQuery("explain analyze execute queryy5(1);");
                        averageTime += work.explainParser(rs5);
                    }
                    long endTime = System.currentTimeMillis();
                    double time = (double) endTime-startTime;
                    time = time/(i*count*5);
                    averageTime = averageTime / (i*count*5);
                    System.out.println(averageTime + " " + i + " " + (endTime-startTime) + " average time " + time);
                    dots.add(new Dot(i, time));
                }
                //work.indexDropper(statement);
            }else{
                System.out.println("Incorrect data");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<Dot> getDots() { return dots; }
}

public class Work {

    Queries q = new Queries();

    public void indexDropper(Statement statement) throws SQLException{
        statement.execute("drop index if exists good_name_idx;");
        statement.execute("drop index if exists order_number_idx;");
        statement.execute("drop index if exists shg_idx;");
        statement.execute("drop index if exists storage_amount_idx;");
        statement.execute("drop index if exists os_amount_idx;");

    }

    public void indexSetter(Statement statement) throws SQLException{
        statement.execute("CREATE INDEX good_name_idx ON public.good (name) where name like 'SASU%';");
        statement.execute("CREATE INDEX order_number_idx ON public.order_has_good (number) where number = 2;");
        statement.execute("CREATE INDEX shg_idx ON public.supply_has_good (good_price) where good_price > 100;");
        statement.execute("CREATE INDEX storage_amount_idx ON public.storage (amount) where amount = 1;");
        statement.execute("CREATE INDEX os_amount_idx ON public.outer_storage (amount) where amount = 1;");
    }

    public ArrayList<Dot> test1Thread(Statement st, int count) throws SQLException, InterruptedException {
        ArrayList<Dot> dots = new ArrayList<>();
        for(int i = 1; i <= 20; i++){
            double averageTime = 0.0;
            long startTime = System.currentTimeMillis();
            for(int j = 0; j < i*count; j++){
                ResultSet rs1 = st.executeQuery("explain analyze " + q.getQuery1("'SASU%'"));
                averageTime += explainParser(rs1);
                ResultSet rs2 = st.executeQuery("explain analyze " + q.getQuery2(2));
                averageTime += explainParser(rs2);
                ResultSet rs3 = st.executeQuery("explain analyze " + q.getQuery3(100));
                averageTime += explainParser(rs3);
                ResultSet rs4 = st.executeQuery("explain analyze " + q.getQuery4(1));
                averageTime += explainParser(rs4);
                ResultSet rs5 = st.executeQuery("explain analyze " + q.getQuery5(1));
                averageTime += explainParser(rs5);
            }
            long endTime = System.currentTimeMillis();
            double time = endTime-startTime;
            time = time/(i*count*5);
            averageTime = averageTime / (i*count*5);
            System.out.println(averageTime + " " + i + " " + (endTime-startTime) + " average time " + time);
            dots.add(new Dot(i, time));

            Thread1.sleep(1000);
        }
        return dots;
    }

    public ArrayList<Dot> test1ThreadWithPrepare(Statement st, int count) throws SQLException, InterruptedException {
        ArrayList<Dot> dots = new ArrayList<>();
        st.execute("prepare query1(varchar) as " + q.getPreparedQuery1());
        st.execute("prepare query2(int) as " + q.getPreparedQuery2());
        st.execute("prepare query3(int) as " + q.getPreparedQuery3());
        st.execute("prepare query4(int) as " + q.getPreparedQuery4());
        st.execute("prepare query5(int) as " + q.getPreparedQuery5());
        for(int i = 1; i <= 20; i++){
            double averageTime = 0.0;
            long startTime = System.currentTimeMillis();
            for(int j = 0; j < i*count; j++){
                ResultSet rs1 = st.executeQuery("explain analyze execute query1(100);");
                averageTime += explainParser(rs1);
                ResultSet rs2 = st.executeQuery("explain analyze execute query2(2);");
                averageTime += explainParser(rs2);
                ResultSet rs3 = st.executeQuery("explain analyze execute query3(100);");
                averageTime += explainParser(rs3);
                ResultSet rs4 = st.executeQuery("explain analyze execute query4(1);");
                averageTime += explainParser(rs4);
                ResultSet rs5 = st.executeQuery("explain analyze execute query5(1);");
                averageTime += explainParser(rs5);
            }
            long endTime = System.currentTimeMillis();
            double time = endTime-startTime;
            time = time/(i*count*5);
            averageTime = averageTime / (i*count*5);
            System.out.println(averageTime + " " + i + " " + (endTime-startTime) + " average time " + time);
            dots.add(new Dot(i, time));
            Thread1.sleep(1000);
        }
        return dots;
    }

    public ArrayList<Dot> test1ThreadWithIndex(Statement statement, int count) throws SQLException, InterruptedException {
        indexDropper(statement);
        ArrayList<Dot> dots = new ArrayList<>();
        indexSetter(statement);

        for(int i = 1; i <= 20; i++){
            double averageTime = 0.0;
            long startTime = System.currentTimeMillis();
            for(int j = 0; j < i*count; j++){
                ResultSet rs1 = statement.executeQuery("explain analyze " + q.getQuery1("'SASU%'"));
                averageTime += explainParser(rs1);
                ResultSet rs2 = statement.executeQuery("explain analyze " + q.getQuery2(2));
                averageTime += explainParser(rs2);
                ResultSet rs3 = statement.executeQuery("explain analyze " + q.getQuery3(100));
                averageTime += explainParser(rs3);
                ResultSet rs4 = statement.executeQuery("explain analyze " + q.getQuery4(1));
                averageTime += explainParser(rs4);
                ResultSet rs5 = statement.executeQuery("explain analyze " + q.getQuery5(1));
                averageTime += explainParser(rs5);
            }
            long endTime = System.currentTimeMillis();
            double time = endTime-startTime;
            time = time/(i*count*6);
            averageTime = averageTime / (i*count*6);
            System.out.println(averageTime + " " + i + " " + (endTime-startTime) + " average time " + time);
            dots.add(new Dot(i, time));
            Thread1.sleep(1000);
        }
        indexDropper(statement);
        return dots;
    }

    public ArrayList<Dot> test1ThreadWithAll(Statement statement, int count) throws SQLException, InterruptedException {
        indexDropper(statement);
        ArrayList<Dot> dots = new ArrayList<>();
        indexSetter(statement);

        statement.execute("prepare queryy1(varchar) as " + q.getPreparedQuery1());
        statement.execute("prepare queryy2(int) as " + q.getPreparedQuery2());
        statement.execute("prepare queryy3(int) as " + q.getPreparedQuery3());
        statement.execute("prepare queryy4(int) as " + q.getPreparedQuery4());
        statement.execute("prepare queryy5(int) as " + q.getPreparedQuery5());

        for(int i = 1; i <= 20; i++){
            double averageTime = 0.0;
            long startTime = System.currentTimeMillis();
            for(int j = 0; j < i*count; j++){
                ResultSet rs1 = statement.executeQuery("explain analyze execute queryy1(100);");
                averageTime += explainParser(rs1);
                ResultSet rs2 = statement.executeQuery("explain analyze execute queryy2(2);");
                averageTime += explainParser(rs2);
                ResultSet rs3 = statement.executeQuery("explain analyze execute queryy3(100);");
                averageTime += explainParser(rs3);
                ResultSet rs4 = statement.executeQuery("explain analyze execute queryy4(1);");
                averageTime += explainParser(rs4);
                ResultSet rs5 = statement.executeQuery("explain analyze execute queryy5(1);");
                averageTime += explainParser(rs5);
            }
            long endTime = System.currentTimeMillis();
            double time = endTime-startTime;
            time = time/(i*count*5);
            averageTime = averageTime / (i*count*5);
            System.out.println(averageTime + " " + i + " " + (endTime-startTime) + " average time " + time);
            dots.add(new Dot(i, time));
            Thread1.sleep(1000);
        }
        indexDropper(statement);
        return dots;
    }

    public double explainParser(ResultSet rs) throws SQLException {
        String[] st = rsToStringArr(rs).clone();
        return Double.parseDouble(st[st.length-1].split(" ")[2]);
    }

    private String[] rsToStringArr(ResultSet rs) throws SQLException {
        Vector<String> ores = new Vector<>();
        while(rs.next()){
            int i = 1;
            StringBuilder st = new StringBuilder();
            try{
                while(true){
                    if(rs.getString(i).length() > 7){
                        st.append(rs.getString(i)).append("\t");
                    }else{
                        st.append(rs.getString(i)).append("\t\t");
                    }
                    i++;

                }
            }catch (ArrayIndexOutOfBoundsException | PSQLException | NullPointerException e){
                ores.add(st.toString());
            }

        }
        return ores.toArray(new String[0]);
    }
}
