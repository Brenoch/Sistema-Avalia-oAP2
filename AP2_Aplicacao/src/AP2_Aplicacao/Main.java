package AP2_Aplicacao;

import dao.*;
import util.*;
import java.sql.Date;
import java.util.Calendar;

public class Main {

    public static void main(String[] args) {

        /*
         * Este é o ponto de entrada da aplicação de avaliação de restaurantes.
         * O código simula o cadastro de clientes, locais, restaurantes e a realização de diversas avaliações
         * (comida, atendimento, ambiente, localização) por diferentes clientes em diferentes restaurantes.
         * Após as avaliações, uma classificação final é calculada para cada restaurante e persistida no banco de dados.
         * Por fim, as classificações são recuperadas do banco para verificação.
         *
         * As classes DAO são utilizadas para interagir com o banco de dados,
         * encapsulando a lógica de persistência para cada tipo de objeto (Cliente, Local, Restaurante, Avaliações, Classificacao).
         *
         * As classes util (Cliente, Local, Restaurante, Avaliacao, AvaliacaoComida, AvaliacaoAtendimento,
         * AvaliacaoAmbiente, AvaliacaoLocalizacao, Classificacao) representam os modelos de dados da aplicação.
         *
         * Cada "BLOCO" no código demonstra um cenário completo de avaliação, desde o cadastro inicial
         * até a recuperação da classificação final do banco de dados.
         */

        ClienteDAO clienteDAO = new ClienteDAO();
        LocalDAO localDAO = new LocalDAO();
        RestauranteDAO restauranteDAO = new RestauranteDAO();
        AvaliacaoComidaDAO avaliacaoComidaDAO = new AvaliacaoComidaDAO();
        AvaliacaoAtendimentoDAO avaliacaoAtendimentoDAO = new AvaliacaoAtendimentoDAO();
        AvaliacaoAmbienteDAO avaliacaoAmbienteDAO = new AvaliacaoAmbienteDAO();
        AvaliacaoLocalizacaoDAO avaliacaoLocalizacaoDAO = new AvaliacaoLocalizacaoDAO();
        ClassificacaoDAO classificacaoDAO = new ClassificacaoDAO();

        System.out.println("--- BLOCo 1: Avaliação de Carlos Eduardo ---");
        System.out.println("Cadastro do cliente:");

        Cliente cliente1 = new Cliente(
                "111.224.323-44",
                "Carlos Eduardo",
                "carl2os.edu@email.com",
                "senhaForte123"
        );
        clienteDAO.salvar(cliente1);
        System.out.println("Cliente salvo, ID: " + cliente1.getIdcliente());

        System.out.println("\nCadastro do local");

        Local local1 = new Local(
                "Rio de Janeiro",
                "Copacabana",
                "Avenida Atlântica",
                1702,
                "22021-001"
        );
        localDAO.salvar(local1);
        System.out.println("Local salvo, ID: " + local1.getIdLocal());

        System.out.println("\nCadastro do restaurante");

        Restaurante restaurante1 = new Restaurante();
        restaurante1.setNome("Cantina do Sabor");
        restaurante1.setLocal(local1);
        restaurante1.setDatasql(new Date(Calendar.getInstance().getTimeInMillis()));
        restauranteDAO.salvar(restaurante1);
        System.out.println("Restaurante salvo, ID: " + restaurante1.getIdrestaurante());

        System.out.println("\nAvaliações do restaurante: ");

        AvaliacaoComida avaliacaoComida1 = new AvaliacaoComida(4.5f);
        avaliacaoComida1.setCliente(cliente1);
        avaliacaoComida1.setRestaurante(restaurante1);
        avaliacaoComidaDAO.salvar(avaliacaoComida1);
        System.out.println("Avaliação de Comida salva, ID: " + avaliacaoComida1.getIdAvaliacao());

        AvaliacaoAtendimento avaliacaoAtendimento1 = new AvaliacaoAtendimento(5.0f);
        avaliacaoAtendimento1.setCliente(cliente1);
        avaliacaoAtendimento1.setRestaurante(restaurante1);
        avaliacaoAtendimentoDAO.salvar(avaliacaoAtendimento1);
        System.out.println("Avaliação de Atendimento salva, ID: " + avaliacaoAtendimento1.getIdAvaliacao());

        AvaliacaoAmbiente avaliacaoAmbiente1 = new AvaliacaoAmbiente(4.0f);
        avaliacaoAmbiente1.setCliente(cliente1);
        avaliacaoAmbiente1.setRestaurante(restaurante1);
        avaliacaoAmbienteDAO.salvar(avaliacaoAmbiente1);
        System.out.println("Avaliação de Ambiente salva, ID: " + avaliacaoAmbiente1.getIdAvaliacao());

        AvaliacaoLocalizacao avaliacaoLocalizacao1 = new AvaliacaoLocalizacao(4.8f);
        avaliacaoLocalizacao1.setCliente(cliente1);
        avaliacaoLocalizacao1.setRestaurante(restaurante1);
        avaliacaoLocalizacaoDAO.salvar(avaliacaoLocalizacao1);
        System.out.println("Avaliação de Localização salva, ID: " + avaliacaoLocalizacao1.getIdAvaliacao());

        System.out.println("\nClassificação do restaurante ");

        Classificacao calculadora1 = new Classificacao(
                restaurante1,
                cliente1,
                avaliacaoComida1,
                avaliacaoAmbiente1,
                avaliacaoAtendimento1,
                avaliacaoLocalizacao1
        );
        float notaFinalCalculada1 = calculadora1.calcularClassificacao();
        System.out.println("Nota final calculada: " + String.format("%.2f", notaFinalCalculada1));

        Classificacao classificacaoFinal1 = new Classificacao();
        classificacaoFinal1.setRestaurante(restaurante1);
        classificacaoFinal1.setCliente(cliente1);
        classificacaoFinal1.setNotaFinal(notaFinalCalculada1);
        classificacaoFinal1.setDataClassificacao(new Date(Calendar.getInstance().getTimeInMillis()));

        classificacaoDAO.salvar(classificacaoFinal1);
        System.out.println("Classificação final salva, ID: " + classificacaoFinal1.getIdClassificacao());

        Classificacao classificacaoRecuperada1 = (Classificacao) classificacaoDAO.buscarPorId(classificacaoFinal1.getIdClassificacao());
        if (classificacaoRecuperada1 != null) {
            System.out.println("Recuperado: ID " + classificacaoRecuperada1.getIdClassificacao() +
                    ", Restaurante: " + classificacaoRecuperada1.getRestaurante().getNome() +
                    ", Cliente: " + classificacaoRecuperada1.getCliente().getNome() +
                    ", Nota: " + classificacaoRecuperada1.getNotaFinal());
        } else {
            System.out.println("Não foi possível recuperar");
        }

        // --- BLOCo 2: Nova Avaliação de Ana Paula ---
        System.out.println("\n\n--- BLOCo 2: Avaliação de Ana Paula ---");
        System.out.println("Cadastro do cliente:");

        Cliente cliente2 = new Cliente(
                "555.416.768-88",
                "Ana Paula Costa",
                "ana.paulaaa2la@email.com",
                "minhaSenhaSegura"
        );
        clienteDAO.salvar(cliente2);
        System.out.println("Cliente salvo, ID: " + cliente2.getIdcliente());

        System.out.println("\nCadastro do local");

        Local local2 = new Local(
                "São Paulo",
                "Jardins",
                "Rua Oscar Freire",
                1234,
                "01426-000"
        );
        localDAO.salvar(local2);
        System.out.println("Local salvo, ID: " + local2.getIdLocal());

        System.out.println("\nCadastro do restaurante");

        Restaurante restaurante2 = new Restaurante();
        restaurante2.setNome("Trattoria da Nona");
        restaurante2.setLocal(local2);
        restaurante2.setDatasql(new Date(Calendar.getInstance().getTimeInMillis()));
        restauranteDAO.salvar(restaurante2);
        System.out.println("Restaurante salvo, ID: " + restaurante2.getIdrestaurante());

        System.out.println("\nAvaliações do restaurante: ");

        AvaliacaoComida avaliacaoComida2 = new AvaliacaoComida(5.0f);
        avaliacaoComida2.setCliente(cliente2);
        avaliacaoComida2.setRestaurante(restaurante2);
        avaliacaoComidaDAO.salvar(avaliacaoComida2);
        System.out.println("Avaliação de Comida salva, ID: " + avaliacaoComida2.getIdAvaliacao());

        AvaliacaoAtendimento avaliacaoAtendimento2 = new AvaliacaoAtendimento(4.5f);
        avaliacaoAtendimento2.setCliente(cliente2);
        avaliacaoAtendimento2.setRestaurante(restaurante2);
        avaliacaoAtendimentoDAO.salvar(avaliacaoAtendimento2);
        System.out.println("Avaliação de Atendimento salva, ID: " + avaliacaoAtendimento2.getIdAvaliacao());

        AvaliacaoAmbiente avaliacaoAmbiente2 = new AvaliacaoAmbiente(5.0f);
        avaliacaoAmbiente2.setCliente(cliente2);
        avaliacaoAmbiente2.setRestaurante(restaurante2);
        avaliacaoAmbienteDAO.salvar(avaliacaoAmbiente2);
        System.out.println("Avaliação de Ambiente salva, ID: " + avaliacaoAmbiente2.getIdAvaliacao());

        AvaliacaoLocalizacao avaliacaoLocalizacao2 = new AvaliacaoLocalizacao(4.9f);
        avaliacaoLocalizacao2.setCliente(cliente2);
        avaliacaoLocalizacao2.setRestaurante(restaurante2);
        avaliacaoLocalizacaoDAO.salvar(avaliacaoLocalizacao2);
        System.out.println("Avaliação de Localização salva, ID: " + avaliacaoLocalizacao2.getIdAvaliacao());

        System.out.println("\nClassificação do restaurante ");

        Classificacao calculadora2 = new Classificacao(
                restaurante2,
                cliente2,
                avaliacaoComida2,
                avaliacaoAmbiente2,
                avaliacaoAtendimento2,
                avaliacaoLocalizacao2
        );
        float notaFinalCalculada2 = calculadora2.calcularClassificacao();
        System.out.println("Nota final calculada: " + String.format("%.2f", notaFinalCalculada2));

        Classificacao classificacaoFinal2 = new Classificacao();
        classificacaoFinal2.setRestaurante(restaurante2);
        classificacaoFinal2.setCliente(cliente2);
        classificacaoFinal2.setNotaFinal(notaFinalCalculada2);
        classificacaoFinal2.setDataClassificacao(new Date(Calendar.getInstance().getTimeInMillis()));

        classificacaoDAO.salvar(classificacaoFinal2);
        System.out.println("Classificação final salva, ID: " + classificacaoFinal2.getIdClassificacao());

        Classificacao classificacaoRecuperada2 = (Classificacao) classificacaoDAO.buscarPorId(classificacaoFinal2.getIdClassificacao());
        if (classificacaoRecuperada2 != null) {
            System.out.println("Recuperado: ID " + classificacaoRecuperada2.getIdClassificacao() +
                    ", Restaurante: " + classificacaoRecuperada2.getRestaurante().getNome() +
                    ", Cliente: " + classificacaoRecuperada2.getCliente().getNome() +
                    ", Nota: " + classificacaoRecuperada2.getNotaFinal());
        } else {
            System.out.println("Não foi possível recuperar");
        }

        // --- BLOCo 3: Nova Avaliação de Pedro Henrique ---
        System.out.println("\n\n--- BLOCo 3: Avaliação de Pedro Henrique ---");
        System.out.println("Cadastro do cliente:");

        Cliente cliente3 = new Cliente(
                "111.888.778-66",
                "Pedro Henrique Lima",
                "peozin.lima@3email.com",
                "senhaTop@789"
        );
        clienteDAO.salvar(cliente3);
        System.out.println("Cliente salvo, ID: " + cliente3.getIdcliente());

        System.out.println("\nCadastro do local");

        Local local3 = new Local(
                "Belo Horizonte",
                "Savassi",
                "Rua Tomé de Souza",
                800,
                "30140-001"
        );
        localDAO.salvar(local3);
        System.out.println("Local salvo, ID: " + local3.getIdLocal());

        System.out.println("\nCadastro do restaurante");

        Restaurante restaurante3 = new Restaurante();
        restaurante3.setNome("Pizzaria Mineira");
        restaurante3.setLocal(local3);
        restaurante3.setDatasql(new Date(Calendar.getInstance().getTimeInMillis()));
        restauranteDAO.salvar(restaurante3);
        System.out.println("Restaurante salvo, ID: " + restaurante3.getIdrestaurante());

        System.out.println("\nAvaliações do restaurante: ");

        AvaliacaoComida avaliacaoComida3 = new AvaliacaoComida(4.0f);
        avaliacaoComida3.setCliente(cliente3);
        avaliacaoComida3.setRestaurante(restaurante3);
        avaliacaoComidaDAO.salvar(avaliacaoComida3);
        System.out.println("Avaliação de Comida salva, ID: " + avaliacaoComida3.getIdAvaliacao());

        AvaliacaoAtendimento avaliacaoAtendimento3 = new AvaliacaoAtendimento(4.2f);
        avaliacaoAtendimento3.setCliente(cliente3);
        avaliacaoAtendimento3.setRestaurante(restaurante3);
        avaliacaoAtendimentoDAO.salvar(avaliacaoAtendimento3);
        System.out.println("Avaliação de Atendimento salva, ID: " + avaliacaoAtendimento3.getIdAvaliacao());

        AvaliacaoAmbiente avaliacaoAmbiente3 = new AvaliacaoAmbiente(3.8f);
        avaliacaoAmbiente3.setCliente(cliente3);
        avaliacaoAmbiente3.setRestaurante(restaurante3);
        avaliacaoAmbienteDAO.salvar(avaliacaoAmbiente3);
        System.out.println("Avaliação de Ambiente salva, ID: " + avaliacaoAmbiente3.getIdAvaliacao());

        AvaliacaoLocalizacao avaliacaoLocalizacao3 = new AvaliacaoLocalizacao(4.5f);
        avaliacaoLocalizacao3.setCliente(cliente3);
        avaliacaoLocalizacao3.setRestaurante(restaurante3);
        avaliacaoLocalizacaoDAO.salvar(avaliacaoLocalizacao3);
        System.out.println("Avaliação de Localização salva, ID: " + avaliacaoLocalizacao3.getIdAvaliacao());

        System.out.println("\nClassificação do restaurante ");

        Classificacao calculadora3 = new Classificacao(
                restaurante3,
                cliente3,
                avaliacaoComida3,
                avaliacaoAmbiente3,
                avaliacaoAtendimento3,
                avaliacaoLocalizacao3
        );
        float notaFinalCalculada3 = calculadora3.calcularClassificacao();
        System.out.println("Nota final calculada: " + String.format("%.2f", notaFinalCalculada3));

        Classificacao classificacaoFinal3 = new Classificacao();
        classificacaoFinal3.setRestaurante(restaurante3);
        classificacaoFinal3.setCliente(cliente3);
        classificacaoFinal3.setNotaFinal(notaFinalCalculada3);
        classificacaoFinal3.setDataClassificacao(new Date(Calendar.getInstance().getTimeInMillis()));

        classificacaoDAO.salvar(classificacaoFinal3);
        System.out.println("Classificação final salva, ID: " + classificacaoFinal3.getIdClassificacao());

        Classificacao classificacaoRecuperada3 = (Classificacao) classificacaoDAO.buscarPorId(classificacaoFinal3.getIdClassificacao());
        if (classificacaoRecuperada3 != null) {
            System.out.println("Recuperado: ID " + classificacaoRecuperada3.getIdClassificacao() +
                    ", Restaurante: " + classificacaoRecuperada3.getRestaurante().getNome() +
                    ", Cliente: " + classificacaoRecuperada3.getCliente().getNome() +
                    ", Nota: " + classificacaoRecuperada3.getNotaFinal());
        } else {
            System.out.println("Não foi possível recuperar");
        }

        // --- BLOCo 4: Nova Avaliação de Fernanda Dantas ---
        System.out.println("\n\n--- BLOCo 4: Avaliação de Fernanda Dantas ---");
        System.out.println("Cadastro do cliente:");

        Cliente cliente4 = new Cliente(
                "567.333.440-55",
                "Fernanda Dantas",
                "feeer.d1antas@email.com",
                "senhaDaFe!456"
        );
        clienteDAO.salvar(cliente4);
        System.out.println("Cliente salvo, ID: " + cliente4.getIdcliente());

        System.out.println("\nCadastro do local");

        Local local4 = new Local(
                "Brasília",
                "Asa Sul",
                "Quadra 202",
                15,
                "70233-000"
        );
        localDAO.salvar(local4);
        System.out.println("Local salvo, ID: " + local4.getIdLocal());

        System.out.println("\nCadastro do restaurante");

        Restaurante restaurante4 = new Restaurante();
        restaurante4.setNome("Café Central");
        restaurante4.setLocal(local4);
        restaurante4.setDatasql(new Date(Calendar.getInstance().getTimeInMillis()));
        restauranteDAO.salvar(restaurante4);
        System.out.println("Restaurante salvo, ID: " + restaurante4.getIdrestaurante());

        System.out.println("\nAvaliações do restaurante: ");

        AvaliacaoComida avaliacaoComida4 = new AvaliacaoComida(4.7f);
        avaliacaoComida4.setCliente(cliente4);
        avaliacaoComida4.setRestaurante(restaurante4);
        avaliacaoComidaDAO.salvar(avaliacaoComida4);
        System.out.println("Avaliação de Comida salva, ID: " + avaliacaoComida4.getIdAvaliacao());

        AvaliacaoAtendimento avaliacaoAtendimento4 = new AvaliacaoAtendimento(4.9f);
        avaliacaoAtendimento4.setCliente(cliente4);
        avaliacaoAtendimento4.setRestaurante(restaurante4);
        avaliacaoAtendimentoDAO.salvar(avaliacaoAtendimento4);
        System.out.println("Avaliação de Atendimento salva, ID: " + avaliacaoAtendimento4.getIdAvaliacao());

        AvaliacaoAmbiente avaliacaoAmbiente4 = new AvaliacaoAmbiente(4.6f);
        avaliacaoAmbiente4.setCliente(cliente4);
        avaliacaoAmbiente4.setRestaurante(restaurante4);
        avaliacaoAmbienteDAO.salvar(avaliacaoAmbiente4);
        System.out.println("Avaliação de Ambiente salva, ID: " + avaliacaoAmbiente4.getIdAvaliacao());

        AvaliacaoLocalizacao avaliacaoLocalizacao4 = new AvaliacaoLocalizacao(5.0f);
        avaliacaoLocalizacao4.setCliente(cliente4);
        avaliacaoLocalizacao4.setRestaurante(restaurante4);
        avaliacaoLocalizacaoDAO.salvar(avaliacaoLocalizacao4);
        System.out.println("Avaliação de Localização salva, ID: " + avaliacaoLocalizacao4.getIdAvaliacao());

        System.out.println("\nClassificação do restaurante ");

        Classificacao calculadora4 = new Classificacao(
                restaurante4,
                cliente4,
                avaliacaoComida4,
                avaliacaoAmbiente4,
                avaliacaoAtendimento4,
                avaliacaoLocalizacao4
        );
        float notaFinalCalculada4 = calculadora4.calcularClassificacao();
        System.out.println("Nota final calculada: " + String.format("%.2f", notaFinalCalculada4));

        Classificacao classificacaoFinal4 = new Classificacao();
        classificacaoFinal4.setRestaurante(restaurante4);
        classificacaoFinal4.setCliente(cliente4);
        classificacaoFinal4.setNotaFinal(notaFinalCalculada4);
        classificacaoFinal4.setDataClassificacao(new Date(Calendar.getInstance().getTimeInMillis()));

        classificacaoDAO.salvar(classificacaoFinal4);
        System.out.println("Classificação final salva, ID: " + classificacaoFinal4.getIdClassificacao());

        Classificacao classificacaoRecuperada4 = (Classificacao) classificacaoDAO.buscarPorId(classificacaoFinal4.getIdClassificacao());
        if (classificacaoRecuperada4 != null) {
            System.out.println("Recuperado: ID " + classificacaoRecuperada4.getIdClassificacao() +
                    ", Restaurante: " + classificacaoRecuperada4.getRestaurante().getNome() +
                    ", Cliente: " + classificacaoRecuperada4.getCliente().getNome() +
                    ", Nota: " + classificacaoRecuperada4.getNotaFinal());
        } else {
            System.out.println("Não foi possível recuperar");
        }

        // --- BLOCo 5: Nova Avaliação de Marcelo Vieira ---
        System.out.println("\n\n--- BLOCo 5: Avaliação de Marcelo Vieira ---");
        System.out.println("Cadastro do cliente:");

        Cliente cliente5 = new Cliente(
                "663.123.444-33",
                "Marcelo Vieira",
                "celo.@email.com",
                "senhadoMarcelo!"
        );
        clienteDAO.salvar(cliente5);
        System.out.println("Cliente salvo, ID: " + cliente5.getIdcliente());

        System.out.println("\nCadastro do local");

        Local local5 = new Local(
                "Porto Alegre",
                "Moinhos de Vento",
                "Rua Padre Chagas",
                500,
                "90570-080"
        );
        localDAO.salvar(local5);
        System.out.println("Local salvo, ID: " + local5.getIdLocal());

        System.out.println("\nCadastro do restaurante");

        Restaurante restaurante5 = new Restaurante();
        restaurante5.setNome("Churrascaria Gaúcha");
        restaurante5.setLocal(local5);
        restaurante5.setDatasql(new Date(Calendar.getInstance().getTimeInMillis()));
        restauranteDAO.salvar(restaurante5);
        System.out.println("Restaurante salvo, ID: " + restaurante5.getIdrestaurante());

        System.out.println("\nAvaliações do restaurante: ");

        AvaliacaoComida avaliacaoComida5 = new AvaliacaoComida(5.0f);
        avaliacaoComida5.setCliente(cliente5);
        avaliacaoComida5.setRestaurante(restaurante5);
        avaliacaoComidaDAO.salvar(avaliacaoComida5);
        System.out.println("Avaliação de Comida salva, ID: " + avaliacaoComida5.getIdAvaliacao());

        AvaliacaoAtendimento avaliacaoAtendimento5 = new AvaliacaoAtendimento(4.8f);
        avaliacaoAtendimento5.setCliente(cliente5);
        avaliacaoAtendimento5.setRestaurante(restaurante5);
        avaliacaoAtendimentoDAO.salvar(avaliacaoAtendimento5);
        System.out.println("Avaliação de Atendimento salva, ID: " + avaliacaoAtendimento5.getIdAvaliacao());

        AvaliacaoAmbiente avaliacaoAmbiente5 = new AvaliacaoAmbiente(4.7f);
        avaliacaoAmbiente5.setCliente(cliente5);
        avaliacaoAmbiente5.setRestaurante(restaurante5);
        avaliacaoAmbienteDAO.salvar(avaliacaoAmbiente5);
        System.out.println("Avaliação de Ambiente salva, ID: " + avaliacaoAmbiente5.getIdAvaliacao());

        AvaliacaoLocalizacao avaliacaoLocalizacao5 = new AvaliacaoLocalizacao(4.5f);
        avaliacaoLocalizacao5.setCliente(cliente5);
        avaliacaoLocalizacao5.setRestaurante(restaurante5);
        avaliacaoLocalizacaoDAO.salvar(avaliacaoLocalizacao5);
        System.out.println("Avaliação de Localização salva, ID: " + avaliacaoLocalizacao5.getIdAvaliacao());

        System.out.println("\nClassificação do restaurante ");

        Classificacao calculadora5 = new Classificacao(
                restaurante5,
                cliente5,
                avaliacaoComida5,
                avaliacaoAmbiente5,
                avaliacaoAtendimento5,
                avaliacaoLocalizacao5
        );
        float notaFinalCalculada5 = calculadora5.calcularClassificacao();
        System.out.println("Nota final calculada: " + String.format("%.2f", notaFinalCalculada5));

        Classificacao classificacaoFinal5 = new Classificacao();
        classificacaoFinal5.setRestaurante(restaurante5);
        classificacaoFinal5.setCliente(cliente5);
        classificacaoFinal5.setNotaFinal(notaFinalCalculada5);
        classificacaoFinal5.setDataClassificacao(new Date(Calendar.getInstance().getTimeInMillis()));

        classificacaoDAO.salvar(classificacaoFinal5);
        System.out.println("Classificação final salva, ID: " + classificacaoFinal5.getIdClassificacao());

        Classificacao classificacaoRecuperada5 = (Classificacao) classificacaoDAO.buscarPorId(classificacaoFinal5.getIdClassificacao());
        if (classificacaoRecuperada5 != null) {
            System.out.println("Recuperado: ID " + classificacaoRecuperada5.getIdClassificacao() +
                    ", Restaurante: " + classificacaoRecuperada5.getRestaurante().getNome() +
                    ", Cliente: " + classificacaoRecuperada5.getCliente().getNome() +
                    ", Nota: " + classificacaoRecuperada5.getNotaFinal());
        } else {
            System.out.println("Não foi possível recuperar");
        }

        // --- BLOCo 6: Como criar um novo Restaurante e Cliente para avaliação ---
        System.out.println("\n\n--- BLOCo 6: Exemplo de Criação e Avaliação de um Novo Restaurante e Cliente ---");

        // 1. Criar um novo Cliente
        System.out.println("Criando um novo cliente:");
        Cliente novoCliente = new Cliente(
                "123.568.979-00",        // CPF do novo cliente
                "Novo Cliente Teste",    // Nome do novo cliente
                "novo.clieente@example.com", // Email do novo cliente
                "senhaNova123"            // Senha do novo cliente
        );
        clienteDAO.salvar(novoCliente); // Salvar o novo cliente no banco de dados
        System.out.println("Novo Cliente salvo, ID: " + novoCliente.getIdcliente());

        // 2. Criar um novo Local para o restaurante
        System.out.println("\nCriando um novo local para o restaurante:");
        Local novoLocal = new Local(
                "Salvador",              // Cidade do novo local
                "Pituba",                // Bairro do novo local
                "Rua Principal",         // Rua do novo local
                100,                     // Número do novo local
                "41810-000"              // CEP do novo local
        );
        localDAO.salvar(novoLocal); // Salvar o novo local no banco de dados
        System.out.println("Novo Local salvo, ID: " + novoLocal.getIdLocal());

        // 3. Criar um novo Restaurante
        System.out.println("\nCriando um novo restaurante:");
        Restaurante novoRestaurante = new Restaurante();
        novoRestaurante.setNome("Novo Restaurante Teste"); // Nome do novo restaurante
        novoRestaurante.setLocal(novoLocal); // Associar o novo local ao restaurante
        novoRestaurante.setDatasql(new Date(Calendar.getInstance().getTimeInMillis())); // Data atual
        restauranteDAO.salvar(novoRestaurante); // Salvar o novo restaurante no banco de dados
        System.out.println("Novo Restaurante salvo, ID: " + novoRestaurante.getIdrestaurante());

        // 4. Realizar Avaliações para o Novo Restaurante pelo Novo Cliente
        System.out.println("\nRealizando avaliações para o novo restaurante:");

        AvaliacaoComida novaAvaliacaoComida = new AvaliacaoComida(3.5f);
        novaAvaliacaoComida.setCliente(novoCliente);
        novaAvaliacaoComida.setRestaurante(novoRestaurante);
        avaliacaoComidaDAO.salvar(novaAvaliacaoComida);
        System.out.println("Nova Avaliação de Comida salva, ID: " + novaAvaliacaoComida.getIdAvaliacao());

        AvaliacaoAtendimento novaAvaliacaoAtendimento = new AvaliacaoAtendimento(4.0f);
        novaAvaliacaoAtendimento.setCliente(novoCliente);
        novaAvaliacaoAtendimento.setRestaurante(novoRestaurante);
        avaliacaoAtendimentoDAO.salvar(novaAvaliacaoAtendimento);
        System.out.println("Nova Avaliação de Atendimento salva, ID: " + novaAvaliacaoAtendimento.getIdAvaliacao());

        AvaliacaoAmbiente novaAvaliacaoAmbiente = new AvaliacaoAmbiente(3.0f);
        novaAvaliacaoAmbiente.setCliente(novoCliente);
        novaAvaliacaoAmbiente.setRestaurante(novoRestaurante);
        avaliacaoAmbienteDAO.salvar(novaAvaliacaoAmbiente);
        System.out.println("Nova Avaliação de Ambiente salva, ID: " + novaAvaliacaoAmbiente.getIdAvaliacao());

        AvaliacaoLocalizacao novaAvaliacaoLocalizacao = new AvaliacaoLocalizacao(3.9f);
        novaAvaliacaoLocalizacao.setCliente(novoCliente);
        novaAvaliacaoLocalizacao.setRestaurante(novoRestaurante);
        avaliacaoLocalizacaoDAO.salvar(novaAvaliacaoLocalizacao);
        System.out.println("Nova Avaliação de Localização salva, ID: " + novaAvaliacaoLocalizacao.getIdAvaliacao());

        // 5. Calcular e Salvar a Classificação Final para o Novo Restaurante e Cliente
        System.out.println("\nClassificando o novo restaurante:");
        Classificacao novaCalculadora = new Classificacao(
                novoRestaurante,
                novoCliente,
                novaAvaliacaoComida,
                novaAvaliacaoAmbiente,
                novaAvaliacaoAtendimento,
                novaAvaliacaoLocalizacao
        );
        float novaNotaFinalCalculada = novaCalculadora.calcularClassificacao();
        System.out.println("Nova Nota final calculada: " + String.format("%.2f", novaNotaFinalCalculada));

        Classificacao novaClassificacaoFinal = new Classificacao();
        novaClassificacaoFinal.setRestaurante(novoRestaurante);
        novaClassificacaoFinal.setCliente(novoCliente);
        novaClassificacaoFinal.setNotaFinal(novaNotaFinalCalculada);
        novaClassificacaoFinal.setDataClassificacao(new Date(Calendar.getInstance().getTimeInMillis()));

        classificacaoDAO.salvar(novaClassificacaoFinal);
        System.out.println("Nova Classificação final salva, ID: " + novaClassificacaoFinal.getIdClassificacao());

        // 6. Recuperar a Classificação para verificar (opcional)
        Classificacao classificacaoRecuperadaNovo = (Classificacao) classificacaoDAO.buscarPorId(novaClassificacaoFinal.getIdClassificacao());
        if (classificacaoRecuperadaNovo != null) {
            System.out.println("Recuperado (Novo): ID " + classificacaoRecuperadaNovo.getIdClassificacao() +
                    ", Restaurante: " + classificacaoRecuperadaNovo.getRestaurante().getNome() +
                    ", Cliente: " + classificacaoRecuperadaNovo.getCliente().getNome() +
                    ", Nota: " + classificacaoRecuperadaNovo.getNotaFinal());
        } else {
            System.out.println("Não foi possível recuperar a nova classificação.");
        }
    }
}