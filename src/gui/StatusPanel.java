package gui;

import checker.*;
import constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.lang.ref.WeakReference;

public class StatusPanel extends JPanel {
    public JLabel winnerLabel;
    public JLabel whitePiecesRemainLabel;
    public JLabel blackPiecesRemainLabel;

    public StatusPanel(){
        JLabel statusPanelLabel = new JLabel(Constants.STATUS_PANEL);
        this.winnerLabel = new JLabel(Constants.CURRENT_TURN);
        this.whitePiecesRemainLabel = new JLabel(Constants.WHITE_PIECES_REMAIN+Constants.CHECKER_COUNT);
        this.blackPiecesRemainLabel = new JLabel(Constants.BLACK_PIECES_REMAIN+Constants.CHECKER_COUNT);
        this.setLayout(new GridBagLayout());
        GridBagConstraints statusConstraints = new GridBagConstraints();
        statusConstraints.gridx = 0;
        statusConstraints.gridy = 0;
        this.add(statusPanelLabel,statusConstraints);
        statusConstraints.gridy++;
        this.add(this.winnerLabel,statusConstraints);
        statusConstraints.gridy++;
        this.add(this.whitePiecesRemainLabel,statusConstraints);
        statusConstraints.gridy++;
        this.add(this.blackPiecesRemainLabel,statusConstraints);
    }


    public void updateWinnerLabel(String winnerText){
        SwingUtilities.invokeLater(new UpdateWinnerLabel(this,winnerText));
    }

    private static class UpdateWinnerLabel implements Runnable{
        private WeakReference<StatusPanel> wStatusPanel;
        private String winnerText;

        UpdateWinnerLabel(StatusPanel statusPanel, String winnerText){
            this.wStatusPanel = new WeakReference<>(statusPanel);
            this.winnerText = winnerText;
        }
        @Override
        public void run() {
            StatusPanel statusPanel = this.wStatusPanel.get();
            if(statusPanel == null){
                return;
            }
            statusPanel.winnerLabel.setText(this.winnerText);
        }
    }

    public void updateRemainingPieces(String text, checker.Color piece){
        SwingUtilities.invokeLater(new UpdateRemainingPieces(this,text,piece));
    }

    private static class UpdateRemainingPieces implements Runnable{
        private WeakReference<StatusPanel> wStatusPanel;
        private String text;
        private checker.Color piece;

        UpdateRemainingPieces(StatusPanel statusPanel, String text, checker.Color piece){
            this.wStatusPanel = new WeakReference<>(statusPanel);
            this.text = text;
            this.piece = piece;
        }

        @Override
        public void run() {
            StatusPanel statusPanel = this.wStatusPanel.get();
            if(statusPanel == null){
                return;
            }
            switch(this.piece){
                case BLACK:
                    statusPanel.blackPiecesRemainLabel.setText(text);
                    break;
                case WHITE:
                    statusPanel.whitePiecesRemainLabel.setText(text);
                    break;
            }
        }
    }
}
