package sample;

public class Queries {
/*
    public String getQuery1(int param){
        return "select g.name, g.id, price " +
                "from good_has_category " +
                "inner join good g " +
                "    on good_has_category.good_id = g.id " +
                "inner join category c " +
                "    on good_has_category.category_id = c.id " +
                "where c.id = " + param +
                "order by price desc;";
    }

    public String getPreparedQuery1(){
        return "select g.name, g.id, price " +
                "from good_has_category " +
                "inner join good g " +
                "    on good_has_category.good_id = g.id " +
                "inner join category c " +
                "    on good_has_category.category_id = c.id " +
                "where c.id = $1 " +
                "order by price desc;";
    }

    public String getQuery2(int param1, int param2){
        return "with good1 as ( " +
                "    select c.name, valuee " +
                "    from char_has_good " +
                "    inner join char c " +
                "        on char_has_good.char_id = c.id " +
                "    inner join good g " +
                "        on char_has_good.good_id = g.id " +
                "    where good_id = " + param1 +
                "    limit 2 " +
                "), good2 as ( " +
                "    select c2.name, valuee " +
                "    from char_has_good " +
                "    inner join char c2 " +
                "        on char_has_good.char_id = c2.id " +
                "    inner join good g2 " +
                "        on char_has_good.good_id = g2.id " +
                "    where good_id = " + param2 +
                "    limit 2 " +
                ") " +
                "select good1.name, good1.valuee, good2.name, good2.valuee from good1 " +
                "inner join good2 on good2.name = good1.name;";
    }

    public String getPreparedQuery2(){
        return "with good1 as ( " +
                "    select c.name, valuee " +
                "    from char_has_good " +
                "    inner join char c " +
                "        on char_has_good.char_id = c.id " +
                "    inner join good g " +
                "        on char_has_good.good_id = g.id " +
                "    where good_id = $1 " +
                "    limit 2 " +
                "), good2 as ( " +
                "    select c2.name, valuee " +
                "    from char_has_good " +
                "    inner join char c2 " +
                "        on char_has_good.char_id = c2.id " +
                "    inner join good g2 " +
                "        on char_has_good.good_id = g2.id " +
                "    where good_id = $2 " +
                "    limit 2 " +
                ") " +
                "select good1.name, good1.valuee, good2.name, good2.valuee from good1 " +
                "inner join good2 on good2.name = good1.name;";
    }

    public String getQuery3(int param){
        return "select g.name as name,\n" +
                "       os.amount as amount\n" +
                "from outer_storage as os\n" +
                "inner join good g on os.good_id = g.id\n" +
                "where provider_id ="+ param + " ;";
    }

    public String getPreparedQuery3(){
        return "select g.name as name,\n" +
                "       os.amount as amount\n" +
                "from outer_storage as os\n" +
                "inner join good g on os.good_id = g.id\n" +
                "where provider_id = $1" + " ;";
    }

    public String getQuery4(int param){
        return "select g.name as good,\n" +
                "       s.date as date,\n" +
                "       shg.amount as amount,\n" +
                "       shg.good_price as good_price,\n" +
                "       (amount * good_price) as current_sum\n" +
                "from supply_has_good as shg\n" +
                "inner join good g\n" +
                "    on shg.good_id = g.id\n" +
                "inner join supply s\n" +
                "    on shg.supply_id = s.id\n" +
                "where supply_id = " + param + ";";
    }

    public String getPreparedQuery4(){
        return "select g.name as good,\n" +
                "       s.date as date,\n" +
                "       shg.amount as amount,\n" +
                "       shg.good_price as good_price,\n" +
                "       (amount * good_price) as current_sum\n" +
                "from supply_has_good as shg\n" +
                "inner join good g\n" +
                "    on shg.good_id = g.id\n" +
                "inner join supply s\n" +
                "    on shg.supply_id = s.id\n" +
                "where supply_id = $1 " + ";";
    }

    public String getQuery5(int param){
        return "select g.name,\n" +
                "       ohg.number as number,\n" +
                "       ohg.price as price,\n" +
                "       (number * ohg.price) as current_sum\n" +
                "from order_has_good as ohg\n" +
                "inner join good g\n" +
                "    on ohg.good_id = g.id\n" +
                "where order_id = " + param + ";";
    }

    public String getPreparedQuery5(){
        return "select g.name,\n" +
                "       ohg.number as number,\n" +
                "       ohg.price as price,\n" +
                "       (number * ohg.price) as current_sum\n" +
                "from order_has_good as ohg\n" +
                "inner join good g\n" +
                "    on ohg.good_id = g.id\n" +
                "where order_id = $1 " + ";";
    }

 */

    String getQuery1(String param){
        return "select g.name,\n" +
                "       c.name,\n" +
                "       c2.name,\n" +
                "       chg.valuee\n" +
                "from storage\n" +
                "inner join good g\n" +
                "    on storage.good_id = g.id\n" +
                "inner join good_has_category ghc\n" +
                "    on g.id = ghc.good_id\n" +
                "inner join category c\n" +
                "    on ghc.category_id = c.id\n" +
                "inner join char_has_good chg\n" +
                "    on g.id = chg.good_id\n" +
                "inner join char c2\n" +
                "    on chg.char_id = c2.id\n" +
                "where g.name like " + param + ";";
    }

    public String getPreparedQuery1(){
        return "select g.name,\n" +
                "       c.name,\n" +
                "       c2.name,\n" +
                "       chg.valuee\n" +
                "from storage\n" +
                "inner join good g\n" +
                "    on storage.good_id = g.id\n" +
                "inner join good_has_category ghc\n" +
                "    on g.id = ghc.good_id\n" +
                "inner join category c\n" +
                "    on ghc.category_id = c.id\n" +
                "inner join char_has_good chg\n" +
                "    on g.id = chg.good_id\n" +
                "inner join char c2\n" +
                "    on chg.char_id = c2.id\n" +
                "where g.name like $1 ;";
    }

    String getQuery2(int param){
        return "select public.order.id,\n" +
                "       worker_id,\n" +
                "       client_id,\n" +
                "       sum_price,\n" +
                "       status_id,\n" +
                "       number\n" +
                "from order_has_good\n" +
                "inner join good g\n" +
                "    on order_has_good.good_id = g.id\n" +
                "inner join public.\"order\"\n" +
                "    on order_has_good.order_id = \"order\".id\n" +
                "where number = "+ param +" ;";
    }

    public String getPreparedQuery2(){
        return "select public.order.id,\n" +
                "       worker_id,\n" +
                "       client_id,\n" +
                "       sum_price,\n" +
                "       status_id,\n" +
                "       number\n" +
                "from order_has_good\n" +
                "inner join good g\n" +
                "    on order_has_good.good_id = g.id\n" +
                "inner join public.\"order\"\n" +
                "    on order_has_good.order_id = \"order\".id\n" +
                "where number = $1 ;";
    }

    String getQuery3(int param){
        return "select s.id,\n" +
                "       date,\n" +
                "       g.name,\n" +
                "       good_price,\n" +
                "       amount\n" +
                "from supply_has_good\n" +
                "inner join supply s on supply_has_good.supply_id = s.id\n" +
                "inner join good g on supply_has_good.good_id = g.id\n" +
                "where good_price > "+ param +";";
    }

    public String getPreparedQuery3(){
        return "select s.id,\n" +
                "       date,\n" +
                "       g.name,\n" +
                "       good_price,\n" +
                "       amount\n" +
                "from supply_has_good\n" +
                "inner join supply s on supply_has_good.supply_id = s.id\n" +
                "inner join good g on supply_has_good.good_id = g.id\n" +
                "where good_price > $1;";
    }

    String getQuery4(int param){
        return "select g.name,\n" +
                "       public.order.id,\n" +
                "       c2.name,\n" +
                "       chg.valuee,\n" +
                "       c.name,\n" +
                "       s.amount\n" +
                "from order_has_good\n" +
                "inner join good g on order_has_good.good_id = g.id\n" +
                "inner join \"order\" on order_has_good.order_id = \"order\".id\n" +
                "inner join char_has_good chg on g.id = chg.good_id\n" +
                "inner join good_has_category ghc on g.id = ghc.good_id\n" +
                "inner join category c on ghc.category_id = c.id\n" +
                "inner join char c2 on chg.char_id = c2.id\n" +
                "inner join storage s on g.id = s.good_id\n" +
                "where number = "+ param +" ;";
    }

    public String getPreparedQuery4(){
        return "select g.name,\n" +
                "       public.order.id,\n" +
                "       c2.name,\n" +
                "       chg.valuee,\n" +
                "       c.name,\n" +
                "       s.amount\n" +
                "from order_has_good\n" +
                "inner join good g on order_has_good.good_id = g.id\n" +
                "inner join \"order\" on order_has_good.order_id = \"order\".id\n" +
                "inner join char_has_good chg on g.id = chg.good_id\n" +
                "inner join good_has_category ghc on g.id = ghc.good_id\n" +
                "inner join category c on ghc.category_id = c.id\n" +
                "inner join char c2 on chg.char_id = c2.id\n" +
                "inner join storage s on g.id = s.good_id\n" +
                "where number = $1 ;";
    }

    String getQuery5(int param){
        return "select good.name,\n" +
                "       p.name,\n" +
                "       c.name,\n" +
                "       chg.valuee\n" +
                "\n" +
                "from good\n" +
                "inner join char_has_good chg on good.id = chg.good_id\n" +
                "inner join char c on chg.char_id = c.id\n" +
                "inner join outer_storage os on good.id = os.good_id\n" +
                "inner join providers p on os.provider_id = p.id\n" +
                "where amount = " + param +";";
    }

    public String getPreparedQuery5(){
        return "select good.name,\n" +
                "       p.name,\n" +
                "       c.name,\n" +
                "       chg.valuee\n" +
                "\n" +
                "from good\n" +
                "inner join char_has_good chg on good.id = chg.good_id\n" +
                "inner join char c on chg.char_id = c.id\n" +
                "inner join outer_storage os on good.id = os.good_id\n" +
                "inner join providers p on os.provider_id = p.id\n" +
                "where amount = $1;";
    }
}
