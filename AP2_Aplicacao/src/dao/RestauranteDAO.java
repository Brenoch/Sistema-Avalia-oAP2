package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;

import util.Restaurante;
import util.Local;
import bd.ConnectionFactory;

public class RestauranteDAO implements BaseDAO {

    @Override
    public void salvar(Object obj) {
        if (obj instanceof Restaurante) {
            Restaurante restaurante = (Restaurante) obj;
            String sql = "INSERT INTO restaurante (nome, id_local, data_criacao) VALUES (?, ?, ?)";

            try (Connection conn = ConnectionFactory.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

                stmt.setString(1, restaurante.getNome());
                if (restaurante.getLocal() != null && restaurante.getLocal().getIdLocal() != 0) {
                    stmt.setInt(2, restaurante.getLocal().getIdLocal());
                } else {
                    throw new SQLException("Local ligado ao restaurante é nulo ou não tem ID.");
                }
                stmt.setDate(3, restaurante.getDatasql());

                int affectedRows = stmt.executeUpdate();

                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            restaurante.setIdrestaurante(generatedKeys.getInt(1));
                            System.out.println("Restaurante salvo (ID: " + restaurante.getIdrestaurante() + ")");
                        } else {
                            System.err.println("Falha ao obter o ID.");
                        }
                    }
                } else {
                    System.err.println("Nenhuma informação foi alterada ao salvar o restaurante.");
                }

            } catch (SQLException e) {
                System.err.println("Erro ao salvar: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("O Objeto não é um restaurante válido. Nada foi salvo");
        }
    }

    @Override
    public Object buscarPorId(int id) {
        String sql = "SELECT id_restaurante, nome, id_local, data_criacao FROM restaurante WHERE id_restaurante = ?";
        Restaurante restaurante = null;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int idLocal = rs.getInt("id_local");

                    LocalDAO localDAO = new LocalDAO();
                    Local local = (Local) localDAO.buscarPorId(idLocal);

                    restaurante = new Restaurante(
                            rs.getInt("id_restaurante"),
                            rs.getString("nome"),
                            local,
                            rs.getDate("data_criacao")
                    );
                    System.out.println("Restaurante encontrado: ID " + restaurante.getIdrestaurante() + ", Nome: " + restaurante.getNome());
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar: " + e.getMessage());
            e.printStackTrace();
        }
        return restaurante;
    }

    @Override
    public ArrayList<Object> listarTodosLazyLoading() {
        return listarTodosEagerLoading();
    }

    @Override
    public ArrayList<Object> listarTodosEagerLoading() {
        ArrayList<Object> restaurantes = new ArrayList<>();
        String sql = "SELECT id_restaurante, nome, id_local, data_criacao FROM restaurante";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idLocal = rs.getInt("id_local");

                LocalDAO localDAO = new LocalDAO();
                Local local = (Local) localDAO.buscarPorId(idLocal);

                Restaurante restaurante = new Restaurante(
                        rs.getInt("id_restaurante"),
                        rs.getString("nome"),
                        local,
                        rs.getDate("data_criacao")
                );
                restaurantes.add(restaurante);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar Restaurantes: " + e.getMessage());
            e.printStackTrace();
        }
        return restaurantes;
    }

    @Override
    public void atualizar(Object obj) {
        if (obj instanceof Restaurante) {
            Restaurante restaurante = (Restaurante) obj;
            String sql = "UPDATE restaurante SET nome = ?, id_local = ?, data_criacao = ? WHERE id_restaurante = ?";

            try (Connection conn = ConnectionFactory.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, restaurante.getNome());
                if (restaurante.getLocal() == null || restaurante.getLocal().getIdLocal() == 0) {
                    throw new SQLException("Local ligado ao restaurante é nulo ou não tem ID.");
                }
                stmt.setInt(2, restaurante.getLocal().getIdLocal());
                stmt.setDate(3, restaurante.getDatasql());
                stmt.setInt(4, restaurante.getIdrestaurante());

                int affectedRows = stmt.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Restaurante atualizado (ID: " + restaurante.getIdrestaurante() + ")");
                } else {
                    System.err.println("Nada foi atualizado.");
                }
            } catch (SQLException e) {
                System.err.println("Erro ao atualizar: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Não foi possível atualizar. O objeto não é um restaurante válido.");
        }
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM restaurante WHERE id_restaurante = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Restaurante excluído (ID: " + id + ")");
            } else {
                System.err.println("Não foi possível excluir. Restaurante não encontrado");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir: " + e.getMessage());
            e.printStackTrace();
        }
    }
}