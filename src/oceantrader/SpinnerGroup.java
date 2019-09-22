package oceantrader;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SpinnerGroup {
    private int groupMax;
    private List<GroupSpinnerNumberModel> models = new ArrayList<GroupSpinnerNumberModel>();

    public SpinnerGroup (int max) {
        this.groupMax = max;
    }

    public SpinnerNumberModel createGroupModel (int val, int min, int max, int step) {
        GroupSpinnerNumberModel model = new GroupSpinnerNumberModel(val, min, max, step, this);
        models.add(model);

        return model;
    }

    public Object getNextValue(int currentVal, int step) {
        int max = getGroupVal() + step;
        if (max > groupMax) {
            return currentVal;
        } else {
            groupValUpdated(max);
            int nextVal = currentVal + step;
            return nextVal;
        }
    }

    public int getGroupVal() {
        int max = 0;
        for (GroupSpinnerNumberModel model : models) {
            max += model.getNumber().intValue();
        }
        return max;
    }

    private void groupValUpdated(int val) {
        System.out.println(val);
    }
    private class GroupSpinnerNumberModel extends SpinnerNumberModel {
        private SpinnerGroup model;
        public GroupSpinnerNumberModel (int val, int min, int max, int step, SpinnerGroup model) {
            super(val, min, max, step);
            this.model = model;
        }

        public Object getNextValue() {
            int currentVal = super.getNumber().intValue();
            int step = super.getStepSize().intValue();

            return model.getNextValue(currentVal, step);
        }

    }
}