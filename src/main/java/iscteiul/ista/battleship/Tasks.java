package iscteiul.ista.battleship;

import java.util.Scanner;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Tarefas de teste em linha de comandos para o jogo Discoveries Battleship Game.
 * <p>
 * Cada tarefa ({@link #taskA()}, {@link #taskB()}, {@link #taskC()} e
 * {@link #taskD()}) lê comandos e dados do teclado através de um
 * {@link Scanner} e testa, de forma incremental, uma parte do jogo: primeiro
 * os navios, depois a frota, a batota e, por fim, as rajadas de tiros.
 * </p>
 * <p>
 * Os comandos reconhecidos são {@code nova}, {@code estado}, {@code mapa},
 * {@code rajada}, {@code ver} e {@code desisto}. Os resultados são escritos
 * através do {@link Logger} da classe.
 * </p>
 *
 * @author fba
 * @see Ship
 * @see Fleet
 * @see Game
 */
public class Tasks {

    /** Logger usado para escrever as mensagens ao utilizador. */
    private static final Logger LOGGER = LogManager.getLogger();

    /** Número de tiros de cada rajada. */
    private static final int NUMBER_SHOTS = 3;

    /** Mensagem de despedida escrita quando o utilizador desiste. */
    private static final String GOODBYE_MESSAGE = "Bons ventos!";

    /** Comando para criar uma nova frota. */
    private static final String NOVAFROTA = "nova";

    /** Comando para terminar a tarefa. */
    private static final String DESISTIR = "desisto";

    /** Comando para disparar uma rajada de tiros. */
    private static final String RAJADA = "rajada";

    /** Comando para ver os tiros válidos já disparados. */
    private static final String VERTIROS = "ver";

    /** Comando de batota: mostra a frota completa. */
    private static final String BATOTA = "mapa";

    /** Comando para ver o estado da frota. */
    private static final String STATUS = "estado";


    /////////////////////////////////////////////////////////////////////////////
    // hereafter one may find some code that can be converted to automatic tests,
    // as long as appropriate changes are made. It also shows that we should
    // develop our code incrementally e.g. first the ships, then the fleet,
    // then some rule checking, then dealing with firing and so on
    /////////////////////////////////////////////////////////////////////////////

    /**
     * Testa a construção de navios.
     * <p>
     * Enquanto houver dados no teclado, lê um navio e, se for válido, lê
     * {@value #NUMBER_SHOTS} posições e indica, para cada uma, se o navio
     * a ocupa ou não.
     * </p>
     */
    public static void taskA() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            Ship s = readShip(in);
            if (s != null)
                for (int i = 0; i < NUMBER_SHOTS; i++) {
                    Position p = readPosition(in);
                    LOGGER.info("{} {}", p, s.occupies(p));
                }
        }
    }

    /**
     * Testa a construção de frotas.
     * <p>
     * Aceita os comandos {@code nova} (constrói uma frota nova),
     * {@code estado} (mostra o estado da frota, se já existir) e
     * {@code desisto} (termina). Qualquer outro comando é rejeitado com uma
     * mensagem.
     * </p>
     */
    public static void taskB() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete lá ...");
            }
            // The other commands are unknown in this task
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Testa a construção de frotas, com a possibilidade de fazer batota.
     * <p>
     * Aceita os mesmos comandos que {@link #taskB()} e ainda {@code mapa},
     * que escreve a frota completa.
     * </p>
     */
    public static void taskC() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                case BATOTA:
                    LOGGER.info(fleet);
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete lá ...");
            }
            // The other commands are unknown in this task
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Testa também a parte do combate, com rajadas de {@value #NUMBER_SHOTS} tiros.
     * <p>
     * Além dos comandos de {@link #taskC()}, aceita {@code rajada} (dispara
     * uma rajada e escreve o número de tiros certeiros, inválidos e repetidos
     * e quantos navios restam) e {@code ver} (mostra os tiros válidos já
     * disparados). Quando já não restam navios, escreve uma mensagem final.
     * </p>
     */
    public static void taskD() {

        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        IGame game = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    game = new Game(fleet);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                case BATOTA:
                    if (fleet != null)
                        game.printFleet();
                    break;
                case RAJADA:
                    if (game != null) {
                        firingRound(in, game);

                        LOGGER.info("Hits: {} Inv: {} Rep: {} Restam {} navios.", game.getHits(), game.getInvalidShots(),
                                game.getRepeatedShots(), game.getRemainingShips());
                        if (game.getRemainingShips() == 0)
                            LOGGER.info("Maldito sejas, Java Sparrow, eu voltarei, glub glub glub...");
                    }
                    break;
                case VERTIROS:
                    if (game != null)
                        game.printValidShots();
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete ...");
            }
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Constrói uma frota a partir dos dados lidos do teclado.
     * <p>
     * Lê navios até o ciclo terminar, adicionando cada navio válido à frota.
     * Se um navio não puder ser adicionado (por exemplo, por tocar noutro),
     * ou se a categoria for desconhecida, escreve uma mensagem e continua.
     * </p>
     * <p>
     * <strong>Nota:</strong> a condição do ciclo é
     * {@code i <= Fleet.FLEET_SIZE}, pelo que pode ler mais um navio do que
     * {@code Fleet.FLEET_SIZE}. Convém confirmar se é intencional.
     * </p>
     *
     * @param in o {@link Scanner} de onde ler os dados
     * @return a frota construída
     */
    static Fleet buildFleet(Scanner in) {
        assert in != null;

        Fleet fleet = new Fleet();
        int i = 0; // i represents the total of successfully created ships

        while (i <= Fleet.FLEET_SIZE) {
            IShip s = readShip(in);
            if (s != null) {
                boolean success = fleet.addShip(s);
                if (success)
                    i++;
                else
                    LOGGER.info("Falha na criacao de {} {} {}", s.getCategory(), s.getBearing(), s.getPosition());
            } else {
                LOGGER.info("Navio desconhecido!");
            }
        }
        LOGGER.info("{} navios adicionados com sucesso!", i);
        return fleet;
    }

    /**
     * Lê os dados de um navio, cria-o e devolve-o.
     * <p>
     * Lê, por esta ordem, a categoria (por exemplo {@code "nau"}), a posição
     * (linha e coluna) e a orientação (um carácter, convertido com
     * {@link Compass#charToCompass(char)}).
     * </p>
     *
     * @param in o {@link Scanner} de onde ler os dados
     * @return o navio criado, ou {@code null} se a categoria for desconhecida
     * @see Ship#buildShip(String, Compass, Position)
     */
    static Ship readShip(Scanner in) {
        String shipKind = in.next();
        Position pos = readPosition(in);
        char c = in.next().charAt(0);
        Compass bearing = Compass.charToCompass(c);
        return Ship.buildShip(shipKind, bearing, pos);
    }

    /**
     * Lê uma posição do mapa.
     * <p>
     * Lê dois inteiros, primeiro a linha e depois a coluna.
     * </p>
     *
     * @param in o {@link Scanner} de onde ler os dados
     * @return a posição lida
     */
    static Position readPosition(Scanner in) {
        int row = in.nextInt();
        int column = in.nextInt();
        return new Position(row, column);
    }

    /**
     * Dispara uma rajada de {@value #NUMBER_SHOTS} tiros sobre a frota, no
     * contexto de um jogo.
     * <p>
     * Para cada tiro lê uma posição e dispara-a com
     * {@link IGame#fire(IPosition)}. Se o tiro afundar um navio, escreve uma
     * mensagem com a categoria desse navio.
     * </p>
     *
     * @param in   o {@link Scanner} de onde ler as posições
     * @param game o jogo em cujo contexto a frota está a ser atacada
     */
    static void firingRound(Scanner in, IGame game) {
        for (int i = 0; i < NUMBER_SHOTS; i++) {
            IPosition pos = readPosition(in);
            IShip sh = game.fire(pos);
            if (sh != null)
                LOGGER.info("Mas... mas... {}s nao sao a prova de bala? :-(", sh.getCategory());
        }

    }

}
