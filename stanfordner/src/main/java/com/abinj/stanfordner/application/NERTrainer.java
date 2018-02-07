package com.abinj.stanfordner.application;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.sequences.SeqClassifierFlags;
import edu.stanford.nlp.util.StringUtils;

import java.util.Properties;

/**
 * For run this class you have to be pass following parameters, which is given below.
 * 1, The .prop file path
 * 2, The training datset (.tsv)
 * 3, the Trained model destination path
 */
public class NERTrainer {

    public static void main(String[] args) {
        if (args.length <3) {
            System.out.println("Need to be provide required parameters!!!");
            return;
        }
        Properties props = StringUtils.propFileToProperties(args[0]);
        SeqClassifierFlags flags = new SeqClassifierFlags(props);

        CRFClassifier<CoreLabel> crf = new CRFClassifier<CoreLabel>(flags);

        crf.train(args[1]);
        crf.serializeClassifier(args[2]);
    }
}
