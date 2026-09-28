package iscteiul.ista.battleship;

/**
 * Contrato para uma posição (célula) na grelha do jogo Discoveries Battleship Game.
 * <p>
 * Uma posição é identificada pela sua linha e coluna, e tem dois estados
 * independentes: pode estar <em>ocupada</em> por (parte de) um navio e pode
 * já ter sido <em>atingida</em> por um tiro.
 * </p>
 *
 * @author fba
 * @see Position
 */
public interface IPosition {

    /**
     * Devolve o número da linha desta posição na grelha.
     *
     * @return o índice da linha
     */
    int getRow();

    /**
     * Devolve o número da coluna desta posição na grelha.
     *
     * @return o índice da coluna
     */
    int getColumn();

    /**
     * Compara esta posição com outro objeto.
     * <p>
     * Duas posições são consideradas iguais quando têm a mesma linha e a mesma coluna.
     * </p>
     *
     * @param other o objeto a comparar com esta posição
     * @return {@code true} se {@code other} representar a mesma posição;
     *         {@code false} caso contrário
     */
    boolean equals(Object other);

    /**
     * Indica se esta posição é adjacente a outra, isto é, se se tocam
     * horizontal, vertical ou diagonalmente.
     * <p>
     * É usado para garantir que dois navios não se tocam quando são
     * posicionados na grelha.
     * </p>
     *
     * @param other a posição a verificar
     * @return {@code true} se as duas posições forem adjacentes;
     *         {@code false} caso contrário
     */
    boolean isAdjacentTo(IPosition other);

    /**
     * Marca esta posição como ocupada por (parte de) um navio.
     */
    void occupy();

    /**
     * Regista um tiro nesta posição, marcando-a como atingida.
     */
    void shoot();

    /**
     * Indica se esta posição está ocupada por (parte de) um navio.
     *
     * @return {@code true} se estiver ocupada; {@code false} caso contrário
     */
    boolean isOccupied();

    /**
     * Indica se esta posição já foi atingida por um tiro.
     *
     * @return {@code true} se já foi atingida; {@code false} caso contrário
     */
    boolean isHit();
}
