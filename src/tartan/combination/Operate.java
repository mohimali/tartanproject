package tartan.combination;

import tartan.*;

/**
 * Created by HomeM5 on 01/12/2014.
 */
public class Operate {

    Combination combo;

    public Operate()
    {

    }
    public Tartan performOperation(Tartan t1, Tartan t2, OPERATION_BINARY operationBINARY) {
        Tartan newTartan = null;

        //COMBINE_CONCATENATION,
//        COMBINE_ODD_EVEN,
//                COMBINE_SUBTRACT,
//                COMBINE_COMPOSITE,
//                COMBINE_ADD,
//                COMBINE_AVERAGE,
//                COMBINE_RANDOMISE


        switch (operationBINARY) {
            case COMBINE_CONCATENATION:
                Concatenation concatenation = new Concatenation();
                newTartan = concatenation.performBinaryOperation(t1, t2);
                break;
            /*
            case COMBINE_ODD_EVEN:
                OddEven oddEven = new OddEven();
                newTartan = oddEven.performBinaryOperation(t1, t2);
                break;

            case COMBINE_SUBTRACT:
                break;
            case COMBINE_COMPOSITE:
                break;

            case COMBINE_ADD:
                break;
            case COMBINE_AVERAGE:
                break;
            case COMBINE_RANDOMISE:
                break;
            default:
                break; */
        } // SWITCH


        return newTartan;
    }

    public Tartan performOperation(Tartan t1, OPERATION_UNARY operation) {

        Tartan newTartan = null;

        switch (operation) {

            case DARKER_COLOUR:
                DarkerColours darkerColours = new DarkerColours();
                newTartan = darkerColours.performUnaryOperation(t1);
                break;

            case BRIGHTER_COLOUR:
                BrighterColours brighterColours = new BrighterColours();
                newTartan = brighterColours.performUnaryOperation(t1);
                break;
            default:
                break;
        }


        return newTartan;
    }

}
