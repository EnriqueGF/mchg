package devep;

import org.bukkit.Bukkit;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.logging.Logger;

public class DB {

    private Logger logger;
    private Connection conn;

    private PreparedStatement addPlayerSwordExp;
    private PreparedStatement getMobExp;
    private PreparedStatement getPlayerSwordLvl;
    private PreparedStatement getPlayerSwordExp;

    public DB(Logger logger) {

        this.logger = logger;
        crearConexion();
        prepareStatements();

    }

    private void prepareStatements() {
        try {

            // Sword Ability
           this.addPlayerSwordExp = conn.prepareStatement("UPDATE players SET players.sword_ability_exp = players.sword_ability_exp + ? WHERE nombre_usuario = ?");
           this.getMobExp = conn.prepareStatement("SELECT exp FROM sword_ability_rewards WHERE entity_name = ?");
           this.getPlayerSwordLvl = conn.prepareStatement("SELECT sword_ability_levels.lvl FROM sword_ability_levels WHERE (SELECT sword_ability_exp FROM players WHERE players.nombre_usuario = ?) >= sword_ability_levels.exp_necesaria ORDER BY sword_ability_levels.lvl DESC LIMIT 0,1");
           this.getPlayerSwordExp = conn.prepareStatement("SELECT players.sword_ability_exp FROM players WHERE nombre_usuario = ?");

           //
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void crearConexion() {

        try{

            Class.forName("com.mysql.jdbc.Driver"); // De esta forma cargamos la clase Driver de MySQL.
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/salvosmc", "root", "");
            Bukkit.getLogger().info("[Classic-HC] Conexión exitosa a la BD");

        } catch(SQLException ex){
            Bukkit.getLogger().warning("Error al abrir Conexión BD: " + ex.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void addPlayerSwordExp(Player player, int exp) {

        try {
            addPlayerSwordExp.setInt(1, exp);
            addPlayerSwordExp.setString(2, player.getName());
            addPlayerSwordExp.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public int getMobExp(Mob mob) {
        int exp = 0;

        try {

            getMobExp.setString(1, mob.getName());
            ResultSet rs = getMobExp.executeQuery();
            while (rs.next())
            {
                exp = rs.getInt("exp");
            }
            rs.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return exp;
    }

    public int getPlayerSwordLvl(Player player) {

        int lvl = 0;

        try {

            getPlayerSwordLvl.setString(1, player.getName());
            ResultSet rs = getPlayerSwordLvl.executeQuery();
            while (rs.next())
            {
                lvl = rs.getInt("lvl");
            }

            rs.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return lvl;
    }

    public int getPlayerSwordExp(Player player) {

        int exp = 0;

        try {
            getPlayerSwordExp.setString(1, player.getName());
            ResultSet rs = getPlayerSwordExp.executeQuery();
            while (rs.next())
            {
                exp = rs.getInt("sword_ability_exp");
            }

            rs.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return exp;
    }

}
