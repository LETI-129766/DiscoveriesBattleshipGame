package iscteiul.ista.battleship;

import java.util.List;

/**
 * Contrato para um navio no jogo Discoveries Battleship Game.
 * <p>
 * Um navio tem uma categoria (por exemplo, galeão ou caravela), um tamanho,
 * uma orientação ({@link Compass}) e ocupa uma lista de posições contíguas
 * na grelha. Permite ainda verificar se está proximo de outros navios ou
 * posições, e registar tiros recebidos.
 * </p>
 *
 * @author fba
 * @see IPosition
 * @see Ship
 */
public interface IShip {

    /**
     * Devolve a categoria do navio (por exemplo, "Galeão", "Fragata", "Nau",
     * "Caravela" ou "Barca").
     *
     * @return o nome da categoria do navio
     */
    String getCategory();

    /**
     * Devolve o tamanho do navio, isto é, o número de posições que ocupa.
     *
     * @return o tamanho do navio
     */
    Integer getSize();

    /**
     * Devolve todas as posições ocupadas pelo navio.
     *
     * @return a lista de posições do navio
     */
    List<IPosition> getPositions();

    /**
     * Devolve a posição de referência do navio (a posição inicial a partir
     * da qual o navio foi colocado na grelha).
     *
     * @return a posição de referência do navio
     */
    IPosition getPosition();

    /**
     * Devolve a orientação do navio na grelha.
     *
     * @return a orientação do navio
     * @see Compass
     */
    Compass getBearing();

    /**
     * Indica se o navio ainda flutua, isto é, se pelo menos uma das suas
     * posições ainda não foi atingida.
     *
     * @return {@code true} se o navio ainda não foi afundado;
     *         {@code false} caso contrário
     */
    boolean stillFloating();

    /**
     * Devolve o índice da linha mais acima ocupada pelo navio.
     *
     * @return o menor índice de linha entre as posições do navio
     */
    int getTopMostPos();

    /**
     * Devolve o índice da linha mais abaixo ocupada pelo navio.
     *
     * @return o maior índice de linha entre as posições do navio
     */
    int getBottomMostPos();

    /**
     * Devolve o índice da coluna mais à esquerda ocupada pelo navio.
     *
     * @return o menor índice de coluna entre as posições do navio
     */
    int getLeftMostPos();

    /**
     * Devolve o índice da coluna mais à direita ocupada pelo navio.
     *
     * @return o maior índice de coluna entre as posições do navio
     */
    int getRightMostPos();

    /**
     * Indica se o navio ocupa a posição indicada.
     *
     * @param pos a posição a verificar
     * @return {@code true} se alguma posição do navio coincidir com {@code pos};
     *         {@code false} caso contrário
     */
    boolean occupies(IPosition pos);

    /**
     * Indica se este navio está demasiado perto de outro navio.
     * <p>
     * Dois navios estão demasiado perto quando alguma das suas posições é
     * adjacente a uma posição do outro. Regra usada no posicionamento da
     * frota, já que os navios não se podem tocar.
     * </p>
     *
     * @param other o outro navio a verificar
     * @return {@code true} se os navios estiverem demasiado perto;
     *         {@code false} caso contrário
     */
    boolean tooCloseTo(IShip other);

    /**
     * Indica se este navio está demasiado perto de uma dada posição, isto é,
     * se alguma das suas posições é adjacente a {@code pos}.
     *
     * @param pos a posição a verificar
     * @return {@code true} se o navio estiver demasiado perto da posição;
     *         {@code false} caso contrário
     */
    boolean tooCloseTo(IPosition pos);

    /**
     * Regista um tiro na posição indicada.
     * <p>
     * Se o navio ocupar essa posição, a posição correspondente é marcada como
     * atingida.
     * </p>
     *
     * @param pos a posição sobre a qual foi disparado o tiro
     */
    void shoot(IPosition pos);
}
