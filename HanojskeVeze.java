/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package cz.itnetwork.hanojskeveze;

import java.util.Scanner;
import java.util.Stack;
import java.util.HashMap;

/**
 *
 * @author Martin
 */
public class HanojskeVeze {

    public static void main(String[] args) {
        HashMap<Integer, Stack<Kotouc>> veze = new HashMap<Integer, Stack<Kotouc>>();
        boolean vyhral = false; 

        /*
         * Vytvoření 3 staků věží
         */
        Stack<Kotouc> vez1 = new Stack<Kotouc>();
        Stack<Kotouc> vez2 = new Stack<Kotouc>();
        Stack<Kotouc> vez3 = new Stack<Kotouc>();

        /*
         * Umístění jednotlivých věží do kolekce HashMap<Integer, Stack<Kotouc>> veze
         * Každá věž je uložena jako Číslo věžě a odkaz na Stack věže
         *   ID    VĚŽ
         *    1   vez1
         *    2   vez2
         *    3   vez3
         */
        veze.put(1, vez1);
        veze.put(2, vez2);
        veze.put(3, vez3);

        /*
         * Vytvoření jednotlivých kotoučů
         * Každý kotouč obsahuje ID a text pro grafické zobrazení
         */
        Kotouc kotouc1 = new Kotouc(1, "####################");
        Kotouc kotouc2 = new Kotouc(2, "  ################ ");
        Kotouc kotouc3 = new Kotouc(3, "    ############  ");
        Kotouc kotouc4 = new Kotouc(4, "      ########   ");
        Kotouc kotouc5 = new Kotouc(5, "        ####    ");

        //Přidání kotoučů do věže 1 - Každý nový kotouč se přidá na konec stacku
        vez1.push(kotouc1);
        vez1.push(kotouc2);
        vez1.push(kotouc3);
        vez1.push(kotouc4);
        vez1.push(kotouc5);

        Scanner sc = new Scanner(System.in);
        
        while (!vyhral) {

            boolean jeSpravne = false; //Kontrolní boolean, který slouží k potvrzení zadání správného vstupu uživatele, pokud uživatel zadal správný input vrátí se hodnota true
            int cisloVeze = 0;
            Kotouc vybranyKotouc = null;

            VykresliVeze(vez1, vez2, vez3); //Tato funkce se stará o vykreslení jednotlivých věží a kotoučů

            /*
             * Následující if podmínka slouží k vyhodnocení hry,
             * pokud jsou kotouče na věži 2 nebo 3 seřazeny od nejmenší k největší,
             * boolean vyhral se nastaví na true, vykreslí se zpráva Vyhrál jsi ! a 
             * dojde k přerušení cyklu while pomocí break; a tím se ukončí samotný program
             */
            if (ZkontrolujPoradiVeze(vez2) == true || ZkontrolujPoradiVeze(vez3) == true) {
                vyhral = true;
                System.out.println("Vyhrál jsi!");
                sc.close();
                break;
            }
            /*
             * Následující cyklus while se stará o kontrolu zadáné hodnoty uživatelem, pokud zadáná hodnota není číslo nebo celé číslo (např.: 1 nebo 10),
             *  tak se celý cyklus bude opakovat do doby dokud uživatel nezadá správnou hodnotu, při zadání špatné hodnoty se uživateli do konzole
             *  vytiskne varovná zpráva
             */
            while (!jeSpravne) {
                try {
                    System.out.print("\nPřesunout kotouč z věže: ");
                    cisloVeze = Integer.parseInt(sc.nextLine()); 
                    System.out.print("\n");

                    /*
                     * Vyhodnocení uživatelem zvolené věže, pokud uživatel vybral věž která neexistuje,
                     *  spustí se Exception Error s vlastní zprávou
                     */
                    if (cisloVeze < 1 || cisloVeze > 3) {
                        throw new Exception("Věž neexistuje, prosím zadejte číslo věže v rozmezí 1 - 3");
                    }

                    /*
                     * Pokud uživatel vybral vež na které nejsou žádné kotouče 
                     *  spustí se Exception Error s vlastní zprávou
                     */
                    if (veze.get(cisloVeze).size() == 0) {
                        throw new Exception("Na věži se nenachází žádné kotouče, prosím zvolte jinou věž");
                    }

                    vybranyKotouc = veze.get(cisloVeze).peek(); //Nastavení aktuálního kotouče => Vybere se věž a poslední kotouč na věži
                    veze.get(cisloVeze).pop(); // Odstranění posledního kotouče na věži

                    jeSpravne = true; //Nastavení booleanu jeSpravne na true, které ukončení zacyklení cyklu while

                } catch (NumberFormatException e) {
                    /*
                     * NumberFormatException error, se spustí při parsování String do Integer
                     * Pro správné zachycení chyby se musí tento catch (NumberFormatException e) blok nacházet před catch (Exception e ) blokem
                     */
                    cisloVeze = 0;
                    System.out.println("Zadaná hodnota není číslo nebo celé číslo, zadejte prosím celé číslo");
                } catch (Exception e) {
                    /*
                     * Exception error, se spustí při jakékoliv chybě, nebo při uživatelem zvolené chybě,
                     */
                    cisloVeze = 0;
                    System.out.println(e.getMessage());
                }
            }

            jeSpravne = false; //Nastavení správnoti uživatelského vstupu na false, pro znovupoužití

            /*
             * Následující cyklus while se stará o kontrolu zadáné hodnoty uživatelem, pokud zadáná hodnota není číslo nebo celé číslo (např.: 1 nebo 10),
             *  tak se celý cyklus bude opakovat do doby dokud uživatel nezadá správnou hodnotu, při zadání špatné hodnoty se uživateli do konzole
             *  vytiskne varovná zpráva
             */
            while (!jeSpravne) {
                try {
                    System.out.print("\nPřesunout kotouč na věž: ");
                    cisloVeze = Integer.parseInt(sc.nextLine()); 
                    System.out.print("\n");

                    /*
                     * Vyhodnocení uživatelem zvolené věže, pokud uživatel vybral věž která neexistuje,
                     *  spustí se Exception Error s vlastní zprávou
                     */
                    if (cisloVeze < 1 || cisloVeze > 3) {
                        throw new Exception("Věž neexistuje, prosím zadejte číslo věže v rozmezí 1 - 3");
                    }

                    /*
                     * Vyhodnocení kotouče a věže => Dochází k porovnání velikostí kotoučů, pokud je vybraný kotouč větší 
                     * něž poslední kotouč na vybrané věži spustí se Exception Error s vlastní zprávou
                     */
                    if (veze.get(cisloVeze).size() != 0 && vybranyKotouc.getID() < veze.get(cisloVeze).peek().getID()) {
                        throw new Exception("Vybraný kotouč je větší než kotouč na věži " + cisloVeze);
                    }

                    veze.get(cisloVeze).push(vybranyKotouc); //Vložení/Přidání nového kotouče na věž
                    jeSpravne = true; //Nastavení booleanu jeSpravne na true, které ukončení zacyklení cyklu while
                } catch (NumberFormatException e) {
                    /*
                     * NumberFormatException error, se spustí při parsování String do Integer
                     * Pro správné zachycení chyby se musí tento catch (NumberFormatException e) blok nacházet před catch (Exception e ) blokem
                     */
                    cisloVeze = 0;
                    System.out.println("Zadaná hodnota není číslo nebo celé číslo, zadejte prosím celé číslo");
                } catch (Exception e) {
                    /*
                     * Exception error, se spustí při jakékoliv chybě, nebo při uživatelem zvolené chybě
                     */
                    cisloVeze = 0;
                    System.out.println(e.getMessage());
                }
            }

        }
    }

    /*
     * Následující funkce slouží k vyhodnocení věže a pořadí kotoučů.
     * Funkce nejdříve vyčte všechny kotouče ze zadané věže, z jednotlivých kotoučů získá jeho ID
     * a přidá jej do textového řetezce s názvem text, jako poslední krok se využije funkce .equals(<zde se zadá text k porovnání>),
     * která slouží k porovnání textu, pokud je text seskládán jako řada čísel => 12345 tak se funkce ukončí vrácením hodnoty booleanu true
     */
    static boolean ZkontrolujPoradiVeze(Stack<Kotouc> vez) {

        String text = "";
        for (Kotouc kotouc : vez) {
            text += kotouc.getID();
        }

        /*
         * Následující text.equals() slouží k porovnání textu, pokud je text stejný podmínka vrátí hodnotu true
         */
        return text.equals("12345");
    }

    /*
     * Následující funkce slouží k vykreslení věží a kotoučů do konzole.
     */
    static void VykresliVeze(Stack<Kotouc> vez1, Stack<Kotouc> vez2, Stack<Kotouc> vez3) {
        System.out.println("    1         2         3"); //Vykreslení čísel věží

        String[] radyKotoucu = new String[5]; //Vytvoření pole => Slouží k zobrazení věží a kotoučů.

        for (int i = 0; i < 5; i++) {
            String text = "";

            text += VykresliKotouc(vez1, i);
            text += VykresliKotouc(vez2, i);
            text += VykresliKotouc(vez3, i);

            radyKotoucu[i] = text; //Zapsání textového řetezce do pole 
        }

        /*
         * Následující cyklus for  postupně získá data z pole radyKotoucu a vykreslí je do konzole
         * Cyklus for čte data z pole radyKotoucu od konce
         * 
         * Toto "otočení" čtení se provádí z důvodu, 
         * že kotouče vykreslené ze Stacku by se zobrazily vzhůru nohama (nejmenší kotouč na spodu),
         *      
         * Čteno ze stacku           Čtení kotoučů po otočení
         *  █████████                        █ 
         *   ███████                        ███  
         *    █████         =>             █████ 
         *     ███                        ███████ 
         *      █                        █████████
         * 
         */
        for (int i = 5; i > 0; i--) {
            System.out.println(radyKotoucu[i - 1]);
        }
    }

    /*
     * Funkce slouží k vytvoření textového řetezce
     */
    static String VykresliKotouc(Stack<Kotouc> vez, int i) {
        String mezeraMeziKotouci = ""; //Mezera mezi jednotlivými věžemi
        String text = "";  //Vytvoření textového řetězce

        if (vez.size() != 0) {
            try {
                if (vez.get(i) != null) {
                    text += mezeraMeziKotouci + vez.get(i).getText();
                }
            } catch (Exception e) {
                text += mezeraMeziKotouci + "          ";
            }
        } else {
            text += mezeraMeziKotouci + "          ";
        }
        return text;
    }
}
