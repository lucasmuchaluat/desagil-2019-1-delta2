package br.pro.hashi.ensino.desagil.aps.model;

public class DeMux extends Gate {
    NandGate nandLeft;
    NandGate nandCenterTop;
    NandGate nandCenterBottom;
    NandGate nandRightTop;
    NandGate nandRightBottom;


    public DeMux() {
        super("DeMux", 2, 2);

        nandLeft = new NandGate();

        nandCenterTop = new NandGate();
        nandCenterTop.connect(1, nandLeft);

        nandCenterBottom = new NandGate();

        nandRightTop = new NandGate();
        nandRightTop.connect(0, nandCenterTop);
        nandRightTop.connect(1, nandCenterTop);

        nandRightBottom = new NandGate();
        nandRightBottom.connect(0, nandCenterBottom);
        nandRightBottom.connect(1, nandCenterBottom);
    }

    @Override
    public boolean read(int outputPin) {
        if (outputPin != 0 && outputPin != 1) {
            throw new IndexOutOfBoundsException(outputPin);
        }
        if (outputPin == 1){
            return nandRightBottom.read();
        }
        return nandRightTop.read();
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nandCenterTop.connect(0, emitter);
                nandCenterBottom.connect(1, emitter);
                break;
            case 1:
                nandLeft.connect(0, emitter);
                nandLeft.connect(1, emitter);
                nandCenterBottom.connect(0, emitter);
                break;

            default:
                throw new IndexOutOfBoundsException(inputPin);
        }
    }
}
