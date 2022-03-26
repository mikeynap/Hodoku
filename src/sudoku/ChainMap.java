
package sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


//

public class ChainMap {
    // index 0, cand 8 = 8
    // index 1, cand 1 = 9
    private Chain[] chains = new Chain[9*9*9];

    private List<Chain> activeChains = new ArrayList<Chain>();

    private Chain prepChain = null;

    private int toggles = 0;
    private int toggleIndex = 0;
    private int toggleCand = 0;

    public Chain getChain(int index, int cand) {
        return chains[index * 9 + cand];
    }

    public void setChain(Chain chain, int index, int cand) {
        chains[index * 9 + cand] = chain;
    }

    public List<Chain> getActiveChains() {
        return activeChains;
    }

    public void resetToggles() {
        toggles = 0;
        toggleIndex = 0;
        toggleCand = 0;
    }

    public void clear() {
        chains = new Chain[9*9*9];
        activeChains.clear();
        resetToggles();
    }

    public Chain prep(int index, int cand, boolean isStrong) {
        Chain fromChain = getChain(index, cand);
        if (fromChain == null) {
            fromChain = new Chain(0, 0, new int[16], 16);
            fromChain.setEntry(0, Chain.makeSEntry(index, cand, isStrong));
        }
        return fromChain;
    }



    public Chain insert(Chain from, int index, int cand) {
        return insert(from.getCellIndex(0), from.getCandidate(0), index, cand, from.isStrong(0));
    }

    public int maybeToggle(Chain prep, int index, int cand) {
        if (prep == null){
            resetToggles();
            return toggles;
        }
        if ((index == toggleIndex && cand == toggleCand) || toggles == 0) {
            toggles++;
        } else {
            resetToggles();
        }

        for (int i = prep.getStart(); i <= prep.getEnd(); i++){
            // if already exists in chain, we should toggle the link type.
            if (prep.isEqual(i, index, cand)) {
                int entry = prep.getChain()[i];
                prep.setEntry(i, prep.setSStrong(entry, !prep.isSStrong(entry)));
                toggleIndex = index;
                toggleCand = cand;
                return toggles;
            }
        }
        toggles = 0;
        return toggles;
    }

    public void delete(int index, int cand){
        Chain chain = getChain(index, cand);
        if (chain == null) {
            return;
        }
        // removing from front means
        int length = chain.getEnd() - chain.getStart();
        if (length <= 2) {
            activeChains.remove(chain);
            for (int i = 0; i < length; i++){
                setChain(null, chain.getCellIndex(i), chain.getCandidate(i));
            }
            return;
        }

        int ind = chain.getEntryIndex(index, cand);
        if (ind == chain.getStart()) {
            setChain(null, index, cand);
            chain.setStart(chain.getStart() + 1);
            setChain(null, index, cand);
        } else if (ind == chain.getEnd()) {
            chain.setEnd(chain.getEnd() - 1);
            setChain(null, index, cand);
        } else {
            // remove from middle, make two new chains.
            Chain left = (Chain)chain.clone();
            Chain right = (Chain)chain.clone();
            left.setEnd(ind - 1);
            right.setStart(ind + 1);
            activeChains.remove(chain);
            activeChains.add(left);
            activeChains.add(right);

            for (int i = chain.getStart(); i <= chain.getEnd(); i++){
                if (i == ind) {
                    continue;
                }
                setChain( i < ind ? left : right, chain.getCellIndex(i), chain.getCandidate(i));
            }
        }

        setChain(null, index, cand);
    }

    // insert link in chain. Can join existing chains IFF they start/end
    // with the from/to.
    // TODO: better logic for finding candidate to connect to. Currently, sometimes connects to the
    // start of the chain that is not visible when the end of the chain is right there....
    // TODO: make it so chain always starts at index 0
    public Chain insert(int fromIndex, int fromCand, int toIndex, int toCand, boolean isStrong) {
        Chain fromChain = getChain(fromIndex, fromCand);
        Chain toChain = getChain(toIndex, toCand);
        if (fromChain == null && toChain == null) {
            int[] chainArr = new int[16];
            chainArr[0] = Chain.makeSEntry(fromIndex, fromCand, isStrong);
            chainArr[1] = Chain.makeSEntry(toIndex, toCand, !isStrong);
            Chain chain = new Chain(0, 1, chainArr, 16);
            setChain(chain, fromIndex, fromCand);
            setChain(chain, toIndex, toCand);
            activeChains.add(chain);
            return chain;
        } else if (fromChain != null) {
            fromChain.appendEntry(toIndex, toCand, !fromChain.isStrong(fromChain.getEnd()));
            setChain(fromChain, toIndex, toCand);
            return fromChain;
        } else if (toChain != null) {
            toChain.prependEntry(fromIndex, fromCand, !toChain.isStrong(toChain.getStart()));
            setChain(toChain, fromIndex, fromCand);
            return toChain;
        }
        return toChain;
    }
}
