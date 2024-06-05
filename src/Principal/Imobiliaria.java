package Principal;


import Models.Endereco;
import Models.Imovel;

import java.util.*;

public class Imobiliaria {

    private Scanner sc = new Scanner(System.in);
    private List<Imovel> imoveis = new ArrayList<>();


    public void exibeMenu() {
        for (int i = 0; i < 35; i++) {
            if (i % 2 == 0) {
                imoveis.add(new Imovel(i, 100, 200, 5, 0, 50000, new Endereco("Ararangua", "Coloninha")));
            } else {
                imoveis.add(new Imovel(i, 100, 200, 5, 1, 50000, new Endereco("Criciuma", "Centro")));
            }
        }
        var menu = """
                1 - Cadastrar de imóveis
                2 - Listar imóveis
                3 - Listar imóveis por tipo
                4 - Listar imóveis por cidade
                5 - Listar imóveis por bairro
                6 - Listar imóveis por preço
                7 - Listar imóveis por número minimo de quartos
                8 - Excluir imóvel
                9 - Alterar imóvel
                
                0 - Sair
                """;
        int opcao = -1;
        while (opcao != 0) {
            System.out.println(menu);
            System.out.println("Escolha sua ação: ");
            try {
                opcao = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                opcao = -1;
            }
            switch (opcao) {
                case 1:
                    cadastrarImoveis();
                    break;
                case 2:
                    listarImoveis();
                    break;
                case 3:
                    listarImoveisPorTipo();
                    break;
                case 4:
                    listarImoveisPorCidade();
                    break;
                case 5:
                    listarImoveisPorBairro();
                    break;
                case 6:
                    listarImoveisPorPreco();
                    break;
                case 7:
                    listarImoveisPorNumeroMinQuartos();
                    break;
                case 8:
                    excluirImovel();
                    break;
                case 9:
                    alterarImovel();
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    private void cadastrarImoveis() {
        try {
            System.out.println("Processo de cadastro iniciado");
            System.out.println("Informe o código do imóvel: ");
            int codigo = sc.nextInt();
            sc.nextLine();
            System.out.println("Informe o tamanho da área construida: ");
            float areaConstruida = sc.nextFloat();
            sc.nextLine();
            System.out.println("Informe o tamanho da área total: ");
            float areaTotal = sc.nextFloat();
            sc.nextLine();
            System.out.println("Informe o número de quartos: ");
            int numeroQuartos = sc.nextInt();
            sc.nextLine();
            System.out.println("Informe o tipo: (0 - Casa ou 1 - Apartamento)");
            int tipo = sc.nextInt();
            sc.nextLine();
            System.out.println("Informe o preço: ");
            float preco = sc.nextFloat();
            sc.nextLine();
            System.out.println("Informe a cidade: ");
            String cidade = sc.nextLine();
            System.out.println("Informe o bairro: ");
            String bairro = sc.nextLine();
            imoveis.add(new Imovel(codigo, areaConstruida, areaTotal, numeroQuartos, tipo, preco, new Endereco(cidade, bairro)));
            System.out.println("Imóvel cadastrado com sucesso!");
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("Formato de escrita inválido!");
            System.out.println("O cadastro foi cancelado!");
        }

    }

    private void listarImoveis() {
        imoveis.forEach(System.out::println);
    }

    private void listarImoveisPorTipo() {
        try {
            System.out.println("Informe o tipo: (0 - Casa ou 1 - Apartamento)");
            int tipo = sc.nextInt();
            // ARRUMAR SE O TIPO NAO FOR 0 ou 1
            sc.nextLine();
            imoveis.stream()
                    .filter(i -> i.getTipo() == tipo)
                    .forEach(System.out::println);
        } catch (InputMismatchException e) {
            System.out.println("Formato de escrita inválido! ");
            System.out.println("Listagem cancelada!");
        }

    }
    private void listarCidades() {
        Set<String> cidades = new HashSet<>();
        System.out.println("Cidades disponíveis: ");
        imoveis.forEach(i -> cidades.add(i.getLocalizacao().getCidade()));
        cidades.forEach(System.out::println);
    }
    private void listarImoveisPorCidade() {
        listarCidades();
        System.out.println("Informe a cidade: ");
        String cidade = sc.nextLine();
        // ARRUMAR SE NÃO ENCONTRAR A CIDADE
        imoveis.stream()
                .filter(i -> i.getLocalizacao().getCidade().equalsIgnoreCase(cidade))
                .forEach(System.out::println);
    }
    private void listarImoveisPorBairro() {
        Set<String> bairros = new HashSet<>();
        listarCidades();
        System.out.println("Informe a cidade: ");
        // ARRUMAR SE NÃO ENCONTRAR A CIDADE
        String cidade = sc.nextLine();
        // TRY CATCH NA CIDADE E BAIRRO
        imoveis.stream()
                .filter(i -> i.getLocalizacao().getCidade().equalsIgnoreCase(cidade))
                .forEach(i -> bairros.add(i.getLocalizacao().getBairro()));

        System.out.println("Bairros disponíveis: ");
        bairros.forEach(System.out::println);
        System.out.println("Informe o bairro: ");
        // ARRUMAR SE NAO ENCONTRAR O BAIRRO
        String bairro = sc.nextLine();
        imoveis.stream()
                .filter(i -> i.getLocalizacao().getBairro().equalsIgnoreCase(bairro))
                .forEach(System.out::println);
    }

    private void listarImoveisPorPreco() {
        String menu = """
                Faixas de preços para a listagem:
                
                1 - R$ 0 a R$ 15.000,00
                2 - R$ 15.000,01 a R$ 50.000,00
                3 - R$ 50.000,01 a R$ 250.000,00
                4 - R$ 250.000,01 a R$ 999.999,99
                5 - A partir de 1.000.000,00
                """;

        try {
            System.out.println(menu);
            int opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao) {
                case 1:
                    imoveis.stream()
                            .filter(i -> i.getPreco() <= 15000)
                            .forEach(System.out::println);
                    break;
                case 2:
                    imoveis.stream()
                            .filter(i -> i.getPreco() > 15000)
                            .filter(i -> i.getPreco() <= 50000)
                            .forEach(System.out::println);
                    break;
                case 3:
                    imoveis.stream()
                            .filter(i -> i.getPreco() > 50000)
                            .filter(i -> i.getPreco() <= 250000)
                            .forEach(System.out::println);
                    break;
                case 4:
                    imoveis.stream()
                            .filter(i -> i.getPreco() > 250000)
                            .filter(i -> i.getPreco() <= 999999.99)
                            .forEach(System.out::println);
                    break;
                case 5:
                    imoveis.stream()
                            .filter(i -> i.getPreco() >= 1000000)
                            .forEach(System.out::println);
                    break;
                default:
                    System.out.println("Opção inválida! ");
                    break;
        }
        } catch (InputMismatchException e) {
            System.out.println("Formato de escrita inválido");
            System.out.println("Listagem cancelada!");
        }
    }
    private void listarImoveisPorNumeroMinQuartos() {
        System.out.println("Informe a quantidade mínima de quartos: ");
        int numQuarto = sc.nextInt();
        imoveis.stream()
                .filter(i -> i.getNumeroQuartos() >= numQuarto)
                .forEach(System.out::println);
    }

    private int encontrarImovel() {
        System.out.println("Informe o código do imóvel: ");
        int codigo = sc.nextInt();
        for (int i = 0; i < imoveis.size(); i++) {
            if (imoveis.get(i).getCodigo() == codigo) {
                return i;
            }
        }
        return -1;
    }

    private void excluirImovel() {
        var imovelIndice = encontrarImovel();
        if (imovelIndice == -1) {
            System.out.println("Imóvel não encontrado!");
        } else {
            imoveis.remove(imovelIndice);
            System.out.println("Imóvel removido com sucesso!");
        }
    }

    private void alterarImovel() {
        String menu = """
                O que você deseja alterar:
                
                0 - Código
                1 - Área construida
                2 - Área total
                3 - Número de quartos
                4 - Tipo
                5 - Preço
                6 - Endereço
                
                9 - Não alterar nada
                """;
        var imovelIndice = encontrarImovel();
        if (imovelIndice == -1) {
            System.out.println("Imóvel não encontrado!");
        } else {
            int opcao = -1;
            while (opcao != 9) {
                System.out.println(menu);
                System.out.println("Escolha sua ação: ");
                try {
                    opcao = sc.nextInt();
                    sc.nextLine();
                } catch (InputMismatchException e) {
                    opcao = -1;
                }
                switch (opcao) {
                    case 0:
                        System.out.println("Informe o novo código: ");
                        int codigo = sc.nextInt();
                        sc.nextLine();
                        imoveis.get(imovelIndice).setCodigo(codigo);
                        System.out.println("Código alterado com sucesso!");
                        break;
                    case 1:
                        System.out.println("Informe a nova área construida: ");
                        float areaConstruida = sc.nextFloat();
                        sc.nextLine();
                        imoveis.get(imovelIndice).setAreaConstruida(areaConstruida);
                        System.out.println("Área construida alterada com sucesso!");
                        break;
                    case 2:
                        System.out.println("Informe a nova área total: ");
                        float areaTotal = sc.nextFloat();
                        sc.nextLine();
                        imoveis.get(imovelIndice).setAreaTotal(areaTotal);
                        System.out.println("Área total alterada com sucesso!");
                        break;
                    case 3:
                        System.out.println("Informe o novo número de quartos: ");
                        int numQuartos = sc.nextInt();
                        sc.nextLine();
                        imoveis.get(imovelIndice).setNumeroQuartos(numQuartos);
                        System.out.println("Número de quartos alterado com sucesso!");
                        break;
                    case 4:
                        System.out.println("Informe o novo tipo: (0 - Casa ou 1 - Apartamento)");
                        int tipo = sc.nextInt();
                        sc.nextLine();
                        imoveis.get(imovelIndice).setTipo(tipo);
                        System.out.println("Tipo alterado com sucesso!");
                        break;
                    case 5:
                        System.out.println("Informe o novo preço: ");
                        float preco = sc.nextFloat();
                        sc.nextLine();
                        imoveis.get(imovelIndice).setPreco(preco);
                        System.out.println("Preço atualizado com sucesso!");
                        break;
                    case 6:
                        System.out.println("Informe a cidade: ");
                        String cidade = sc.nextLine();
                        System.out.println("Informe o bairro: ");
                        String bairro = sc.nextLine();
                        imoveis.get(imovelIndice).setLocalizacao(new Endereco(cidade, bairro));
                        System.out.println("Novo endereço adicionado com sucesso!");
                        break;
                    case 9:
                        System.out.println("Voltando para o menu principal...");
                    default:
                        System.out.println("Opção inválida!");
                }
            }
        }
    }
}
