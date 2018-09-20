package blackjack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;

public class HomeScreen extends Application{
	
	private Pane canvas;
	private Scene scene;
	private Label label;
	private Label playerLabel;
	private Label dealerLabel;
	private LinkedList<GUICommand> list;
	private ArrayList<Image> spades;
	private ArrayList<Image> hearts;
	private ArrayList<Image> clubs;
	private ArrayList<Image> diamonds;
	private Image back;
	private Button consoleGame;
	private Button fileGame;
	private ButtonType hit;
	private ButtonType stand;
	private ButtonType split;
	private BlackJackControl bjGame;
	private static int IMGWIDTH = 92;
	private static int IMGHEIGHT= 140;
	private static int X = 100;
	private static int YPLAYERNOSPLIT = 350;
	private static int YDEALERNOSPLIT = 75;
	private static int YPLAYERSPLIT = 320;
	private static int YDEALERSPLIT = 20;
	

	public static void main (String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		initUI(primaryStage);
		initImgList();
		selectGameInput(primaryStage);
	}

	private void animateGame() {
		
		playerLabel.setFont(Font.font("Serif",FontWeight.NORMAL, 20));
		playerLabel.relocate(20, YPLAYERNOSPLIT);
		dealerLabel.setText("Dealer");
		dealerLabel.setFont(Font.font("Serif",FontWeight.NORMAL, 20));
		dealerLabel.relocate(20, YDEALERSPLIT);
		label.setFont(Font.font("Serif",FontWeight.NORMAL, 10));
		label.relocate(X, 300);
		
		canvas.getChildren().addAll(playerLabel,dealerLabel);
		for (GUICommand c: list) {System.out.println(c);}
		canvas.getChildren().removeAll(consoleGame, fileGame);
		ArrayList<ArrayList<ImageView>> playerHand = new ArrayList<ArrayList<ImageView>>();
		ArrayList<ArrayList<ImageView>> dealerHand = new ArrayList<ArrayList<ImageView>>();
		playerHand.add(new ArrayList<ImageView>());
		dealerHand.add(new ArrayList<ImageView>());
		GUICommand command = null;
		do {
			if (list.size() == 0) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Dialog with Custom Actions");
				alert.setHeaderText("");
				alert.setContentText("Choose your option.");
				if (bjGame.canPlayerSplit()) {
					alert.getButtonTypes().setAll(hit, stand, split);
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == hit){bjGame.playerHit();}
					else if (result.get() == stand) {bjGame.playerStand();}
					else {bjGame.playerSplit();}
				}else {
					alert.getButtonTypes().setAll(hit, stand);
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == hit){bjGame.playerHit();}
					else {bjGame.playerStand();}
				}
				continue;
			}
			command = list.removeLast();
			label.setText(command.toString());
			if(command.getType() == GUICommand.Type.PARTICIPANT) {
				if(command.getAction() == GUICommand.Action.DRAW ||command.getAction() == GUICommand.Action.HIT) {
					ImageView iv = new ImageView();
					iv.setFitWidth(46*2);
					iv.setFitHeight(70*2);
					canvas.getChildren().addAll(iv);
					try {
						iv.setImage(cardImg(command.getCard()));
						if (command.getObj() == GUICommand.Object.PLAYER) {
							if(playerHand.get(command.to()).size() == 0) iv.relocate(X, YPLAYERNOSPLIT);
							else iv.relocate(playerHand.get(command.to()).get(playerHand.get(command.to()).size()-1).getLayoutX() + 50, YPLAYERNOSPLIT);
							playerHand.get(command.to()).add(iv);
						}
						else {
							if(dealerHand.get(command.to()).size() == 0) iv.relocate(X, YDEALERNOSPLIT);
							else iv.relocate(dealerHand.get(command.to()).get(dealerHand.get(command.to()).size()-1).getLayoutX() + 50, YDEALERNOSPLIT);
							dealerHand.get(command.to()).add(iv);
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}else if (command.getAction() == GUICommand.Action.SPLIT) {
					if (command.getObj() == GUICommand.Object.PLAYER) {
						playerHand.add(new ArrayList<ImageView>());
						playerHand.get(0).get(0).relocate(X, YPLAYERSPLIT);
						playerHand.get(1).get(0).relocate(X, YPLAYERSPLIT+150);
						playerHand.get(1).add(playerHand.get(0).remove(1));
					}else {
						dealerHand.add(new ArrayList<ImageView>());
						dealerHand.get(0).get(0).relocate(X, YDEALERSPLIT);
						dealerHand.get(1).get(0).relocate(X, YDEALERSPLIT+150);
						dealerHand.get(1).add(dealerHand.get(0).remove(1));
					}
				}
			}
		}while(command.getSystemAction() != GUICommand.SystemAction.WINNER);
		
	}

	private Image cardImg(Card c) throws FileNotFoundException {
		if (c == null) return back;
		switch(c.getSuit()) {
		case S: 	return spades.get(c.getRank()-1);
		case C: 	return clubs.get(c.getRank()-1);
		case D: 	return diamonds.get(c.getRank()-1);
		default:return hearts.get(c.getRank()-1);
		}
	}
	private void selectGameInput(final Stage primaryStage) {
		bjGame = new BlackJackControl();
		list = new LinkedList<GUICommand>();
		label.setText("Welcome to COMP3004 A1 Black Jack Game!\nI am your navigator Haiyue Sun.\nPleasr select input type below.");
		label.setFont(Font.font("Serif",FontWeight.NORMAL, 20));
		label.relocate(20, 20);
		
		consoleGame.relocate(30, 100);
		fileGame.relocate(200, 100);
		consoleGame.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				TextInputDialog dialog = new TextInputDialog();
				dialog.setTitle("Text Input Dialog");
				dialog.setHeaderText("Could you tell me your name please?");
				dialog.setContentText("Please enter your name:");
				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()){
				    bjGame.creatConsoleGame(result.get(), list);
				    label.setText("OK. "+result.get()+" Nice to meet you.");
				    playerLabel.setText(result.get());
					animateGame();
				}
			}});
		fileGame.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();
				File selectedFile = fileChooser.showOpenDialog(primaryStage);
				String path = selectedFile.getAbsolutePath();
				ArrayList<String> scenario = readFile(path);
				bjGame.creatFileGame(scenario, list);
				playerLabel.setText("Pupplet Player(AI)");
				animateGame();
			}});
		canvas.getChildren().addAll(label,consoleGame,fileGame);
		
	}

	private ArrayList<String> readFile(String fileName) {
		//String fileName = view.readString("Please enter the file name you want to load: ");
		while(true) {
			String path = fileName;
			String line = null;
			String a = "";
			try {
		        // FileReader reads text files in the default encoding.
		        FileReader fileReader = new FileReader(path);
		        // Always wrap FileReader in BufferedReader.
		        BufferedReader bufferedReader = new BufferedReader(fileReader);
		        	while((line = bufferedReader.readLine()) != null) {
		            a += line;
	        		}
		        // Always close files.
		        bufferedReader.close();
		        String[] arr = a.split("\\s+");
			    ArrayList<String> strList = new ArrayList<String>();
			    strList.addAll(Arrays.asList(arr));
				return strList;
		    }
		    catch(FileNotFoundException ex) {
		        System.out.println("Unable to open file '" + fileName + "'");
		    }
		    catch(IOException ex) {
		        System.out.println("Error reading file '"+ fileName + "'");
		    }
		}
	}
	private void initImgList() throws FileNotFoundException, FileIntegrityException {
		spades = new ArrayList<Image>();
		hearts = new ArrayList<Image>();
		clubs = new ArrayList<Image>();
		diamonds = new ArrayList<Image>();
		File cardsDir = new File("src/main/resources/card/");
		back = new Image(new FileInputStream("src/main/resources/card/Back.jpg"));
		//Spades
		FilenameFilter imgFilter = new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith("Spade") && name.endsWith("jpg");
			}
		};
		File[] cardsFile = cardsDir.listFiles(imgFilter);
		for (File f : cardsFile) {
			spades.add(new Image(new FileInputStream(f)));
		}
		
		if (spades.size() != 13) {throw new FileIntegrityException("Spade", spades.size());}
		//Hearts
		imgFilter = new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith("Heart") && name.endsWith("jpg");
			}
		};
		cardsFile = cardsDir.listFiles(imgFilter);
		for (File f : cardsFile) {
			hearts.add(new Image(new FileInputStream(f)));
		}
		if (hearts.size() != 13) {throw new FileIntegrityException("Heart", hearts.size());}
		
		//Clubs
		imgFilter = new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith("Club") && name.endsWith("jpg");
			}
		};
		cardsFile = cardsDir.listFiles(imgFilter);
		for (File f : cardsFile) {
				clubs.add(new Image(new FileInputStream(f)));
		}
		if (clubs.size() != 13) {throw new FileIntegrityException("Club", clubs.size());}
		
		//Diamonds
		imgFilter = new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith("Diamond") && name.endsWith("jpg");
			}
		};
		cardsFile = cardsDir.listFiles(imgFilter);
		for (File f : cardsFile) {
				diamonds.add(new Image(new FileInputStream(f)));
		}
		if (diamonds.size() != 13) {throw new FileIntegrityException("Diamon", diamonds.size());}
		
	}

	private void initUI(Stage primaryStage) {
		canvas = new Pane();
		scene = new Scene(canvas, 800, 600);
		consoleGame = new Button("Console Input");
		fileGame = new Button("File Iuput");
		hit = new ButtonType("Hit");
		stand = new ButtonType("Stand");
		split = new ButtonType("Split");
		label = new Label();
		playerLabel = new Label();
		dealerLabel = new Label();
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("COMP 3004 A1 Black Jack Game");
		primaryStage.show();
		
	}

	/*private void initUI(Stage primaryStage) throws FileNotFoundException {
		canvas = new Pane();
		scene = new Scene(canvas, 800, 600);
		Image img = new Image(new FileInputStream("src/main/resources/card/Heart1.jpg"));
		ImageView imgView = new ImageView();
		imgView.setImage(img);
		imgView.relocate(10, 10);
		
		
		File cardsDir = new File("src/main/resources/card/");
		FilenameFilter  imgFilter = new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith("jpg");
			}
		};
		File[] cardsFile = cardsDir.listFiles(imgFilter);
		Image[] cardsImg = new Image[cardsFile.length];
		int idx = 0;
		for (File f : cardsFile) {
		
				cardsImg[idx] = new Image(new FileInputStream(f));
				idx++;
			
		}
		ImageView cardView = new ImageView();
		cardView.setImage(cardsImg[2]);
		cardView.setFitWidth(46*2);
		cardView.setFitHeight(70*2);
		cardView.relocate(10, 10);
		
		canvas.getChildren().addAll(cardView);
		
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("COMP 3004 A1 Black Jack Game");
		primaryStage.show();
	}*/


	/*private void initUI(Stage primaryStage) throws InterruptedException{
		canvas = new Pane();
		canvas.setStyle("-fx-background-color: pink");
		
		Label label = new Label("Black Jack");
		label.setText("out put");
		label.setFont(Font.font("Serif",FontWeight.NORMAL, 20));
		label.relocate(20, 20);
		
		Label label2 = new Label(" Line 2 ");
		label2.setFont(Font.font("Serif",FontWeight.NORMAL, 20));
		label2.relocate(20, 40);

		
		
		canvas.getChildren().addAll(label);
		
		scene = new Scene(canvas,320, 200);
		primaryStage.setScene(scene);
		primaryStage.setTitle("COMP 3004 A1 Black Jack Game");
		primaryStage.show();
		
		canvas.getChildren().addAll(label2);
		
	}*/
	

}
