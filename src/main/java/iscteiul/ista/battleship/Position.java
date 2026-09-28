package iscteiul.ista.battleship;

import java.util.Objects;

/**
 * Implementação de {@link IPosition}: uma célula da grelha do jogo
 * Discoveries Battleship Game.
 * <p>
 * Cada posição é identificada pela sua linha e coluna (que não mudam depois
 * de criada) e tem dois estados independentes: <em>ocupada</em> por (parte de)
 * um navio e <em>atingida</em> por um tiro. Ambos começam a {@code false}.
 * </p>
 *
 * @author fba
 * @see IPosition
 */
public class Position implements IPosition {

    /** Índice da linha desta posição na grelha. */
    private int row;

    /** Índice da coluna desta posição na grelha. */
    private int column;

    /** Indica se a posição está ocupada por (parte de) um navio. */
    private boolean isOccupied;

    /** Indica se a posição já foi atingida por um tiro. */
    private boolean isHit;

    /**
     * Cria uma posição na linha e coluna indicadas.
     * <p>
     * A posição começa livre (não ocupada) e ainda não atingida.
     * </p>
     *
     * @param row    o índice da linha
     * @param column o índice da coluna
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.isOccupied = false;
        this.isHit = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getColumn() {
        return column;
    }

    /**
     * Calcula o código hash desta posição.
     * <p>
     * <strong>Atenção:</strong> o cálculo usa a linha, a coluna, o estado
     * "ocupada" e o estado "atingida", enquanto {@link #equals(Object)} só
     * compara linha e coluna. Por isso, duas posições iguais segundo
     * {@code equals} podem ter códigos hash diferentes se os seus estados
     * forem diferentes, o que não respeita o contrato geral de
     * {@link Object#hashCode()}.
     * </p>
     *
     * @return o código hash desta posição
     */
    @Override
    public int hashCode() {
        return Objects.hash(column, isHit, isOccupied, row);
    }

    /**
     * Compara esta posição com outro objeto.
     * <p>
     * Duas posições são iguais se o outro objeto for um {@link IPosition}
     * com a mesma linha e a mesma coluna. Os estados "ocupada" e "atingida"
     * não são considerados.
     * </p>
     *
     * @param otherPosition o objeto a comparar com esta posição
     * @return {@code true} se {@code otherPosition} for a mesma posição
     *         (mesma referência, ou mesma linha e coluna);
     *         {@code false} caso contrário
     */
    @Override
    public boolean equals(Object otherPosition) {
        if (this == otherPosition)
            return true;
        if (otherPosition instanceof IPosition) {
            IPosition other = (IPosition) otherPosition;
            return (this.getRow() == other.getRow() && this.getColumn() == other.getColumn());
        } else {
            return false;
        }
    }

    /**
     * Indica se esta posição é adjacente a outra.
     * <p>
     * Duas posições são adjacentes quando a diferença entre as linhas e a
     * diferença entre as colunas são ambas, em valor absoluto, no máximo 1.
     * Isto inclui vizinhas horizontais, verticais e diagonais e, também,
     * a própria posição comparada consigo mesma.
     * </p>
     *
     * @param other a posição a verificar
     * @return {@code true} se as posições forem adjacentes (ou a mesma);
     *         {@code false} caso contrário
     */
    @Override
    public boolean isAdjacentTo(IPosition other) {
        return (Math.abs(this.getRow() - other.getRow()) <= 1 && Math.abs(this.getColumn() - other.getColumn()) <= 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void occupy() {
        isOccupied = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shoot() {
        isHit = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isHit() {
        return isHit;
    }

    /**
     * Devolve uma representação textual desta posição.
     *
     * @return uma cadeia no formato {@code "Linha = <linha> Coluna = <coluna>"}
     */
    @Override
    public String toString() {
        return ("Linha = " + row + " Coluna = " + column);
    }

}
