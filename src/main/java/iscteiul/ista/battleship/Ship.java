package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe base abstrata para todos os navios do jogo Discoveries Battleship Game.
 * <p>
 * Guarda a informação comum a qualquer navio: a categoria, a orientação
 * ({@link Compass}), a posição de referência e a lista de posições que ocupa
 * na grelha. As subclasses concretas ({@link Barge}, {@link Caravel},
 * {@link Carrack}, {@link Frigate} e {@link Galleon}) definem o tamanho
 * do navio ({@link #getSize()}) e preenchem a lista de posições a partir da
 * posição de referência e da orientação.
 * </p>
 * <p>
 * Os navios são normalmente criados através de
 * {@link #buildShip(String, Compass, Position)}.
 * </p>
 *
 * @author fba
 * @see IShip
 * @see IPosition
 */
public abstract class Ship implements IShip {

    /** Categoria usada para identificar um {@link Galleon}. */
    private static final String GALEAO = "galeao";

    /** Categoria usada para identificar uma {@link Frigate}. */
    private static final String FRAGATA = "fragata";

    /** Categoria usada para identificar uma {@link Carrack}. */
    private static final String NAU = "nau";

    /** Categoria usada para identificar uma {@link Caravel}. */
    private static final String CARAVELA = "caravela";

    /** Categoria usada para identificar uma {@link Barge}. */
    private static final String BARCA = "barca";

    /**
     * Cria um navio da categoria indicada.
     * <p>
     * As categorias reconhecidas são {@code "galeao"}, {@code "fragata"},
     * {@code "nau"}, {@code "caravela"} e {@code "barca"} (em minúsculas e
     * sem acentos).
     * </p>
     *
     * @param shipKind a categoria do navio a criar
     * @param bearing  a orientação do navio na grelha
     * @param pos      a posição de referência do navio
     * @return o navio criado, ou {@code null} se {@code shipKind} não
     *         corresponder a nenhuma categoria conhecida
     */
    static Ship buildShip(String shipKind, Compass bearing, Position pos) {
        Ship s;
        switch (shipKind) {
            case BARCA:
                s = new Barge(bearing, pos);
                break;
            case CARAVELA:
                s = new Caravel(bearing, pos);
                break;
            case NAU:
                s = new Carrack(bearing, pos);
                break;
            case FRAGATA:
                s = new Frigate(bearing, pos);
                break;
            case GALEAO:
                s = new Galleon(bearing, pos);
                break;
            default:
                s = null;
        }
        return s;
    }


    /** Categoria do navio (por exemplo, "galeao" ou "barca"). */
    private String category;

    /** Orientação do navio na grelha. */
    private Compass bearing;

    /** Posição de referência do navio, indicada na sua criação. */
    private IPosition pos;

    /**
     * Posições ocupadas pelo navio. Começa vazia e é preenchida pelas
     * subclasses.
     */
    protected List<IPosition> positions;


    /**
     * Cria um navio com a categoria, orientação e posição de referência
     * indicadas.
     * <p>
     * A lista de posições ocupadas começa vazia; cabe às subclasses
     * preenchê-la.
     * </p>
     *
     * @param category a categoria do navio
     * @param bearing  a orientação do navio (não pode ser {@code null})
     * @param pos      a posição de referência do navio (não pode ser {@code null})
     */
    public Ship(String category, Compass bearing, IPosition pos) {
        assert bearing != null;
        assert pos != null;

        this.category = category;
        this.bearing = bearing;
        this.pos = pos;
        positions = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Devolve as posições ocupadas pelo navio.
     * <p>
     * A lista devolvida é a própria lista interna do navio, não uma cópia.
     * </p>
     *
     * @return a lista de posições do navio
     */
    public List<IPosition> getPositions() {
        return positions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IPosition getPosition() {
        return pos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Compass getBearing() {
        return bearing;
    }

    /**
     * {@inheritDoc}
     * <p>
     * O navio continua a flutuar enquanto pelo menos uma das suas posições
     * não tiver sido atingida.
     * </p>
     */
    @Override
    public boolean stillFloating() {
        for (int i = 0; i < getSize(); i++)
            if (!getPositions().get(i).isHit())
                return true;
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTopMostPos() {
        int top = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() < top)
                top = getPositions().get(i).getRow();
        return top;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBottomMostPos() {
        int bottom = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() > bottom)
                bottom = getPositions().get(i).getRow();
        return bottom;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLeftMostPos() {
        int left = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() < left)
                left = getPositions().get(i).getColumn();
        return left;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRightMostPos() {
        int right = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() > right)
                right = getPositions().get(i).getColumn();
        return right;
    }

    /**
     * {@inheritDoc}
     * <p>
     * A comparação é feita com {@link IPosition#equals(Object)}, ou seja,
     * apenas pela linha e coluna.
     * </p>
     *
     * @param pos a posição a verificar (não pode ser {@code null})
     */
    @Override
    public boolean occupies(IPosition pos) {
        assert pos != null;

        for (int i = 0; i < getSize(); i++)
            if (getPositions().get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Devolve {@code true} se alguma posição do outro navio estiver demasiado
     * perto deste navio, de acordo com {@link #tooCloseTo(IPosition)}.
     * </p>
     *
     * @param other o outro navio a verificar (não pode ser {@code null})
     */
    @Override
    public boolean tooCloseTo(IShip other) {
        assert other != null;

        Iterator<IPosition> otherPos = other.getPositions().iterator();
        while (otherPos.hasNext())
            if (tooCloseTo(otherPos.next()))
                return true;

        return false;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Usa {@link IPosition#isAdjacentTo(IPosition)}, por isso são também
     * consideradas as posições na diagonal e a própria posição.
     * </p>
     */
    @Override
    public boolean tooCloseTo(IPosition pos) {
        for (int i = 0; i < this.getSize(); i++)
            if (getPositions().get(i).isAdjacentTo(pos))
                return true;
        return false;
    }


    /**
     * {@inheritDoc}
     * <p>
     * Se o navio não ocupar a posição indicada, nada acontece.
     * </p>
     *
     * @param pos a posição atingida (não pode ser {@code null})
     */
    @Override
    public void shoot(IPosition pos) {
        assert pos != null;

        for (IPosition position : getPositions()) {
            if (position.equals(pos))
                position.shoot();
        }
    }


    /**
     * Devolve uma representação textual do navio.
     *
     * @return uma cadeia no formato
     *         {@code "[<categoria> <orientação> <posição de referência>]"}
     */
    @Override
    public String toString() {
        return "[" + category + " " + bearing + " " + pos + "]";
    }

}
