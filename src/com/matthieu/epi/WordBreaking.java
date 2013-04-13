package com.matthieu.epi;

import java.util.*;

public class WordBreaking implements Solution {
    public static class Trie {
        boolean is_word=false;
        Map<String, Trie> next=new HashMap<String, Trie>();

        public void addWord(String w) {
            if (w.equals("")) {
                is_word=true;
                return;
            }
            String first_letter = w.substring(0,1);
            if (!next.containsKey(first_letter)) {
                Trie next_trie = new Trie();
                next.put(first_letter, next_trie);
            }
            next.get(first_letter).addWord(w.substring(1));
        }

        public static Trie createTrie(String []w) {
            Trie res = new Trie();
            for (String word:w)
                res.addWord(word);
            return res;
        }

        public boolean findWord(String w) {
            if (w.equals(""))
                return is_word;
            String first_letter = w.substring(0,1);
            if (!next.containsKey(first_letter))
                return false;

            return next.get(first_letter).findWord(w.substring(1));
        }
    }

    public List<String> findConcatenatedWords(Trie trie, String words) {
        if (words.equals("")) {
            return null;
        }
        for (int i=1; i<words.length();i++) {
            if (trie.findWord(words.substring(0,i))) {
                if (i == words.length()-1) {
                    return new ArrayList<String>(Arrays.asList(words));
                }
                List<String> followers = findConcatenatedWords(trie, words.substring(i));
                if (followers != null) {
                    followers.add(0, words.substring(0,i));
                    return followers;
                }
            }
        }
        return null;
    }

    @Override
    public void solveProblem() {
        String dictionary[] = lorem.replaceAll("[^a-zA-Z]"," ").split(" ");
        Trie trie = Trie.createTrie(dictionary);

        String test = "loremelitsitpurusestat";


        System.out.println("Testing "+test+" returns "+findConcatenatedWords(trie, test));
    }

    private final String lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris sit amet volutpat purus. Nulla facilisi. Nulla egestas fermentum augue laoreet adipiscing. Fusce suscipit massa nunc. Maecenas libero neque, euismod ac convallis vitae, convallis at augue. Praesent sollicitudin venenatis est, sit amet bibendum nibh tempus ut. Nullam laoreet, eros et sodales semper, felis tortor faucibus tellus, pellentesque faucibus libero nulla quis elit. Cras mollis quam dolor, eu lobortis lectus. Pellentesque nec faucibus justo. Curabitur ultricies semper varius. Etiam hendrerit iaculis sem, sed tempus felis sollicitudin non. Etiam viverra risus lorem. Nullam fermentum vestibulum tincidunt. Aenean id purus ut sapien vehicula rutrum. Etiam sollicitudin, leo condimentum tincidunt ultrices, eros turpis aliquet purus, ac scelerisque sem ligula sit amet justo. Curabitur nisi orci, ornare eget bibendum a, dapibus vel libero. Mauris vel risus nisi. Quisque eget purus diam, id malesuada nisl. Aliquam ac urna blandit tellus porttitor vehicula. Nullam ut tellus id magna imperdiet convallis nec sed lectus. Nulla eu sem massa, a accumsan justo. Proin sollicitudin posuere porttitor. Praesent interdum magna viverra neque ultricies vitae volutpat elit mollis. Nullam mollis pellentesque tellus, sed semper orci ullamcorper eu. Cras varius aliquam blandit. Pellentesque nec aliquet arcu. Sed eget libero est. Nunc a lacus ante. Ut quis lorem felis, ut vehicula metus. Sed imperdiet lobortis justo et malesuada. Fusce vehicula ipsum consectetur sapien volutpat malesuada. Praesent pharetra aliquam lacus sit amet vulputate. Pellentesque eros quam, volutpat vel mattis vitae, ultrices nec purus. Donec posuere iaculis blandit. Donec diam elit, convallis nec luctus in, tristique et justo. Cras est odio, ornare in porttitor vitae, convallis nec urna. Pellentesque cursus, eros vitae vehicula viverra, massa urna euismod mauris, quis tincidunt nisl sapien euismod arcu. Quisque non turpis ipsum, ac faucibus augue. Sed a ligula quis lorem pellentesque interdum quis eu mauris. Curabitur in dictum felis. Mauris ornare eleifend scelerisque. Aliquam felis lectus, fringilla fermentum hendrerit at, feugiat in dolor. Sed id hendrerit elit. Sed molestie justo eu est convallis elementum. Cras luctus, nisi eu bibendum tempor, enim quam varius diam, ut tincidunt enim odio vitae sem. Curabitur bibendum cursus quam sed imperdiet. Nunc bibendum risus vitae nisi congue interdum. Suspendisse vitae est sapien. Fusce ut risus at metus hendrerit tristique. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Aliquam erat volutpat. Sed mauris leo, facilisis nec scelerisque nec, vehicula sit amet enim. Duis id vehicula sapien. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Vivamus viverra erat a felis euismod vehicula. Suspendisse ac nulla lectus. Nulla ultrices, nibh in blandit tempus, magna est vestibulum justo, id venenatis purus lorem a erat. Donec quis orci ante. Curabitur in nisl nunc. Quisque nisl lorem, elementum at viverra non, pretium ac est. Donec augue justo, dapibus vitae porttitor in, semper ut orci. Sed quis felis tortor. Suspendisse quis neque a erat ullamcorper scelerisque sit amet at nisl. Proin libero dolor, aliquet id ullamcorper eu, suscipit sit amet augue. Morbi egestas nisl non purus aliquam hendrerit ac vitae eros. Duis pharetra massa non sapien semper id blandit arcu viverra. Pellentesque tincidunt eros eget dui sollicitudin luctus. Suspendisse facilisis lacus a urna eleifend blandit. Cras nisi diam, elementum in scelerisque dictum, imperdiet nec quam. Proin auctor risus vel sapien laoreet mollis hendrerit urna blandit. Donec non quam ac libero bibendum venenatis. Fusce convallis, dolor vitae bibendum tristique, arcu orci auctor sem, pellentesque facilisis eros odio eu quam. Nam in metus quam, eget congue orci. Fusce vitae neque augue, non lobortis velit. Nullam ultrices scelerisque est, sit amet euismod purus facilisis at. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cras ultrices dolor id lorem condimentum pretium. Aenean convallis blandit tempor. In sed ipsum erat, nec sollicitudin tellus. Quisque ac nisl non erat iaculis vulputate id in diam. Nulla facilisis, est at tristique convallis, velit turpis tincidunt arcu, ac consequat libero diam vel felis. Curabitur egestas velit tortor, at eleifend erat. Cras hendrerit commodo volutpat. Cras varius ipsum vitae sapien congue eget luctus nisl mattis. Nullam sed congue quam. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Aenean accumsan nisi vitae eros mollis eu vehicula lorem rhoncus. Cras rhoncus lobortis sem in placerat. Vivamus a neque massa. Donec ac ipsum a lectus laoreet lacinia. Aliquam erat volutpat. Duis diam lectus, ornare vitae sodales vel, sagittis eget mi. Vivamus ante dolor, posuere a viverra ac, viverra porttitor libero. Nulla eu erat tellus. Phasellus ac felis vitae erat pharetra porttitor. Praesent quis nibh eget ante ullamcorper auctor. Nam vitae risus non odio tincidunt bibendum quis eget purus. Aliquam ac erat arcu. Pellentesque congue, lacus iaculis imperdiet accumsan, quam dui laoreet libero, ut tempor lorem nisi et mi. Sed sit amet ipsum nisl, et lobortis dui. Nulla a consequat neque. Pellentesque ultrices ipsum sed eros iaculis ac ullamcorper turpis adipiscing. In hac habitasse platea dictumst. Etiam viverra viverra congue. Etiam congue urna nec nulla euismod eu venenatis orci dictum. Curabitur vitae tellus aliquet tortor elementum sollicitudin eget at sapien. In eu eros metus. Curabitur rutrum, dolor vel dictum vulputate, urna mauris vestibulum enim, at malesuada neque lacus non mi. Morbi imperdiet placerat iaculis. Curabitur iaculis turpis quis est vestibulum ac sollicitudin lectus semper. Pellentesque pretium odio at augue iaculis at consectetur tortor lobortis. Nam eu nisi sit amet turpis venenatis consequat in eget est. Nullam diam tortor, vulputate sit amet volutpat ac, sagittis sed metus. Nunc felis magna, condimentum a ultricies ac, porttitor sed lectus.";
}
