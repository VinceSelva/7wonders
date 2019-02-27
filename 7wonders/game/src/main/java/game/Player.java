package game;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.net.URISyntaxException;

public class Player {

    private String nom;
    Socket connexion;
    private Wonder wonder;

    public Player(String un_joueur) {
        setNom(un_joueur);

        System.out.println(nom +" > creation");
        try {
            // préparation de la connexion
            connexion = IO.socket("http://" + "127.0.0.1" + ":" + "12345");

            // abonnement à la connexion
            connexion.on("connect", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println(getNom() + " > connecte");
                    System.out.println(getNom()+" > envoi de mon nom");
                    connexion.emit(Messages.MON_NOM, getNom());
                }
            });


            // réception de la wonder
            connexion.on(Messages.ENVOI_DE_MERVEILLE, new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    // réception du JSON
                    JSONObject wonderJSON = (JSONObject)objects[0];
                    try {
                        // conversion du JSON en wonder
                        String n = wonderJSON.getString("nom");
                        // les wonders ont toutes une ressource vide, pour illustrer avec un objet avec plus qu'une seule propriété
                        String ressource = wonderJSON.getString("ressource");
                        //Wonder m = new Wonder(n);
                        //m.setwonderMaterials(ressource);

                        // mémorisation de la wonder
                        //System.out.println(nom+" > j'ai recu "+m);
                        //setwonder(m);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            // réception de la main
            connexion.on(Messages.ENVOI_DE_MAIN, new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    // réception de l'objet JSON : une main
                    JSONObject mainJSON = (JSONObject)objects[0];
                    try {
                        Main m = new Main();
                        // la main ne contient qu'une liste de card, c'est un JSONArray
                        JSONArray cardsJSON = mainJSON.getJSONArray("cards");
                        // on recrée chaque card
                        for(int j = 0 ; j < cardsJSON.length(); j++) {
                            JSONObject cardJSON = (JSONObject) cardsJSON.get(j);
                            //Card c = new Card(cardsJSON.getString("name"));
                            //m.addCard(c);
                        }
                        System.out.println(nom+" > j'ai recu "+m);

                        // le joueur a reçu, il joue
                        jouer(m);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (
                URISyntaxException e) {
            e.printStackTrace();
        }
    }


    private void jouer(Main m) {
        // ne fonctionne pas dans Android
        JSONObject pieceJointe = new JSONObject(m.getcards().get(0)) ;

        // dans Android, il faudrait faire :
        // JSONObject pieceJointe = new JSONObject();
        // pieceJointe.put("name", m.getcards().get(0).getName());
        // et il faudrait faire cela entre try / catch

        System.out.println(nom + " > je joue "+m.getcards().get(0));
        connexion.emit(Messages.JE_JOUE, pieceJointe);
    }

    public void démarrer() {
        // connexion effective
        if (connexion != null) connexion.connect();
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }


    public static final void main(String  [] args) {
        Player j = new Player("toto");
        j.démarrer();
    }

    public void setwonder(Wonder wonder) {
        this.wonder = wonder;
    }

    public Wonder getwonder() {
        return wonder;
    }
}