import java.util.*;

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean eow = false;

    TrieNode () {
        for (int i = 0; i < children.length; i++) {
            children[i] = null;
        }
    }
}

public class test {

    public static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        List<String> dictionary = Arrays.asList("e","k","c","harqp","h","gsafc","vn","lqp","soy","mr","x","iitgm","sb","oo","spj","gwmly","iu","z","f","ha","vds","v","vpx","fir","t","xo","apifm","tlznm","kkv","nxyud","j","qp","omn","zoxp","mutu","i","nxth","dwuer","sadl","pv","w","mding","mubem","xsmwc","vl","farov","twfmq","ljhmr","q","bbzs","kd","kwc","a","buq","sm","yi","nypa","xwz","si","amqx","iy","eb","qvgt","twy","rf","dc","utt","mxjfu","hm","trz","lzh","lref","qbx","fmemr","gil","go","qggh","uud","trnhf","gels","dfdq","qzkx","qxw");
        String sentence = "ikkbp miszkays wqjferqoxjwvbieyk gvcfldkiavww vhokchxz dvypwyb bxahfzcfanteibiltins ueebf lqhflvwxksi dco kddxmckhvqifbuzkhstp wc ytzzlm gximjuhzfdjuamhsu gdkbmhpnvy ifvifheoxqlbosfww mengfdydekwttkhbzenk wjhmmyltmeufqvcpcxg hthcuovils ldipovluo aiprogn nusquzpmnogtjkklfhta klxvvlvyh nxzgnrveghc mpppfhzjkbucv cqcft uwmahhqradjtf iaaasabqqzmbcig zcpvpyypsmodtoiif qjuiqtfhzcpnmtk yzfragcextvx ivnvgkaqs iplazv jurtsyh gzixfeugj rnukjgtjpim hscyhgoru aledyrmzwhsz xbahcwfwm hzd ygelddphxnbh rvjxtlqfnlmwdoezh zawfkko iwhkcddxgpqtdrjrcv bbfj mhs nenrqfkbf spfpazr wrkjiwyf cw dtd cqibzmuuhukwylrnld dtaxhddidfwqs bgnnoxgyynol hg dijhrrpnwjlju muzzrrsypzgwvblf zbugltrnyzbg hktdviastoireyiqf qvufxgcixvhrjqtna ipfzhuvgo daee r nlipyfszvxlwqw yoq dewpgtcrzausqwhh qzsaobsghgm ichlpsjlsrwzhbyfhm ksenb bqprarpgnyemzwifqzz oai pnqottd nygesjtlpala qmxixtooxtbrzyorn gyvukjpc s mxhlkdaycskj uvwmerplaibeknltuvd ocnn frotscysdyclrc ckcttaceuuxzcghw pxbd oklwhcppuziixpvihihp";
        
        TrieNode root = new TrieNode();
        for (String str : dictionary) {
            TrieNode curr = root;
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                int idx = ch - 'a';
                if (curr.children[idx] == null) {
                    curr.children[idx] = new TrieNode();
                }

                curr = curr.children[idx];
            }
            curr.eow = true;
        }

        String[] words = sentence.split(" ");
        StringBuilder res = new StringBuilder();

        outer: for (String word : words) {
            // System.out.println(word);
            TrieNode curr = root;
            StringBuilder resWord = new StringBuilder();
            // if (word.equals("r")) System.out.println("--------------------------------------");
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                // if (word.equals("r")) System.out.println("--------------------------------------");
                resWord.append(ch);
                int idx = ch - 'a';
                if (curr.children[idx] == null) {
                    res.append(word + " ");
                    continue outer;
                }
                
                curr = curr.children[idx];
                if (curr.eow) {
                    res.append(resWord.append(" "));
                    continue outer;
                }
            }

            res.append(word + " ");
        }

        System.out.println(res.toString().substring(0, res.length()-1));
    }
}