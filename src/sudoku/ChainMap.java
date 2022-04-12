
package sudoku;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeMap;


//

public class ChainMap {
    // index 0, cand 8 = 8
    // index 1, cand 1 = 9
    private Chain[] chains = new Chain[9*9*9 + 9];

    private ArrayList<Chain> activeChains = new ArrayList<Chain>();

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

    public void setChain(Chain chain) {
        for (int i = chain.getStart(); i <= chain.getEnd(); i++) {
            int entry = chain.getChain()[i];
            setChain(chain, Chain.getSCellIndex(entry), Chain.getSCandidate(entry));
        }
    }

    private void pruneChains() {
        ListIterator<Chain> iter = activeChains.listIterator();
        while (iter.hasNext()) {
            if (iter.next().getSimpleLength() == 0) {
                iter.remove();
            }
        }
    }

    public ArrayList<Chain> getActiveChains() {
        pruneChains();
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



    public Chain tryInsert(Chain from, int index, int cand) {
        if (from == null) {
            return null;
        }

        int idx = from.canInsertValidLink(index, cand);
        if (idx == -1) {
            return null;
        }


        Chain to = getChain(index, cand);
        if (to == null) {
            from.insertAt(idx, index, cand);
            setChain(from);
            if (!activeChains.contains(from)) {
                activeChains.add(from);
            }
            return from;
        }

        if (to == from) {
            return null;
        }

        if (to.isComplete()) {
            System.out.println("Not combining with completed chain");
            return null;
        }

        System.out.printf("Combining: %s and %s\n", from, to);
        if (Sudoku2.canSee(from.getEndIndex(),from.getEndCandidate(), to.getStartIndex(), to.getStartCandidate())) {
            from.append(to); // check
        }   else if (Sudoku2.canSee(from.getStartIndex(), from.getStartCandidate(), to.getEndIndex(), to.getEndCandidate())) {
            from.prepend(to);
        } else if (Sudoku2.canSee(from.getEndIndex(),from.getEndCandidate(), to.getEndIndex(), to.getEndCandidate())) {
            to.reverse();
            from.append(to);
        } else if (Sudoku2.canSee(from.getStartIndex(), from.getStartCandidate(), to.getStartIndex(), to.getStartCandidate())) {
            System.out.println("Start only sees Start, not combining chains");
            return null;
        } else {
            System.out.println("Chains cannot be combined");
            return null;
        }

        if (!activeChains.contains(from)) {
            activeChains.add(from);
        }
        setChain(from);
        activeChains.remove(to);
        return from;
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
            chain.popHead();
        } else if (ind == chain.getEnd()) {
            chain.popTail();
            setChain(null, index, cand);
        } else {
            // remove from middle, make two new chains.
            Chain left = (Chain)chain.clone();
            Chain right = (Chain)chain.clone();
            left.setEnd(ind - 1);
            right.makeChainStartAt(ind + 1);
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
}
