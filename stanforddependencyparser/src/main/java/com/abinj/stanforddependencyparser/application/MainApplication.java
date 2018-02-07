package com.abinj.stanforddependencyparser.application;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.parser.nndep.DependencyParser;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.trees.GrammaticalStructure;

import java.io.StringReader;
import java.util.List;

/**
 * Demonstrates how to first use the tagger, then use the NN dependency
 * parser. Note that the parser will not work on untagged text.
 *
 * For run this class you can pass two parameters they are given below
 * 1, Tagger model path
 * 2, Universal dependency model path
 *
 * Note: If the parameters not provided then will use the default models
 *
 */
public class MainApplication {

    public static void main(String[] args) {
        String modelPath = DependencyParser.DEFAULT_MODEL;
        String taggerPath = "edu/stanford/nlp/models/pos-tagger/english-left3words/english-left3words-distsim.tagger";

        for (int argIndex = 0; argIndex < args.length; ) {
            switch (args[argIndex]) {
                case "-tagger":
                    taggerPath = args[argIndex + 1];
                    argIndex += 2;
                    break;
                case "-model":
                    modelPath = args[argIndex + 1];
                    argIndex += 2;
                    break;
                default:
                    throw new RuntimeException("Unknown argument " + args[argIndex]);
            }
        }

        String text = "I saw a girl with a telescope";

        MaxentTagger tagger = new MaxentTagger(taggerPath);
        DependencyParser parser = DependencyParser.loadFromModelFile(modelPath);

        DocumentPreprocessor tokenizer = new DocumentPreprocessor(new StringReader(text));
        for (List<HasWord> sentence : tokenizer) {
            List<TaggedWord> tagged = tagger.tagSentence(sentence);
            GrammaticalStructure gs = parser.predict(tagged);
            System.out.println(gs);
        }
    }

}
