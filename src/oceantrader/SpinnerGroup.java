package oceantrader;

import javax.swing.SpinnerNumberModel;
import java.util.ArrayList;
import java.util.List;

public class SpinnerGroup {

    private int groupMax;
    private List<GroupSpinnerNumberModel> models = new ArrayList<>();

    protected SpinnerGroup(int max) {
        this.groupMax = max;
    }

    protected SpinnerNumberModel createGroupModel(int val, int min, int max, int step) {
        GroupSpinnerNumberModel model = new GroupSpinnerNumberModel(val, min, max, step, this);
        models.add(model);
        return model;
    }

    private Object getNextValue(int currentVal, int step) {
        int max = getGroupVal() + step;
        return (max > groupMax) ? currentVal : (currentVal + step);
    }

    private int getGroupVal() {
        int max = 0;
        for (GroupSpinnerNumberModel model : models) {
            max += model.getNumber().intValue();
        }
        return max;
    }

    private class GroupSpinnerNumberModel extends SpinnerNumberModel {

        private SpinnerGroup model;

        private GroupSpinnerNumberModel(int val, int min, int max, int step, SpinnerGroup model) {
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