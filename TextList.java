/**
 *
 *This class represents a list of words arranged in alphabetical order without duplicates of words
 *
 *@author Gal Ben Artzi
 */
public class TextList
{
    WordNode _head;

    /**
     * Empty constructor - initializes the list
     *
     * The time complexity of this method is O(n)
     * The space complexity of this method is O(1)
     */
    public TextList () {
        _head = null;
    }

    /**
     * Constructor that receives text and builds a list of words from it in alphabetically order and without duplicate words.
     * First, arrange all the words in an unsorted list and then use the merge sort To make the list sorted alphabetically
     * The time complexity of this method is O(nlogn)
     * The space complexity of this method is O(1)
     */
    public TextList (String text){ 
        if (text.length() == 0)                   // If an empty string is obtained, a list is initialized with the first empty node
            this._head = new WordNode ();

        else {
            while (text.charAt(0) == ' ' ) {        // Removes spaces at the beginning of the text
                text = text.substring(1);
                if (text.length() == 0){                 //If there are no words after the spaces the list will be empty
                    this._head = new WordNode ();
                }
                break;
            }
            TextList list = new TextList();             // Create a temporary list that will contain all the separated words
            int start = text.indexOf(" ");         //Search for the first space to separate the word
            if (start != -1){
                list.addToData(text.substring(0,start));                    //The first word is the head
                text = text.substring(start + 1);                         //Remove the first word from the text

                while (text != "" && start != -1){                            //A loop that works as long as words remain in the string
                    int num = text.indexOf(" ");                              // Search space to separate the word
                    if (num != -1){                                           // If it is possible to separate into words we will add the word
                        list.addToBeginningList(text.substring(0,num));                     // Add the word to the list
                        text = text.substring(num+1);                         // //Remove the word from the text
                    }
                    else   
                        break;
                }
                if (text.length() != 0)               //Checks if there is a last word a
                    list.addToBeginningList(text);                                               // Add the last word to the list

                this._head = (mergeSort(list._head));          // Merg sorting to sort the list
            }

            else { 
                this._head = new WordNode (text);     //Returns the sorted list head to the head of the generated list
            }
        }
    }

    /**
     * Add a given word to the list.
     * @param word The word that added to the list
     * The time complexity of this method is O(n^2)
     * The space complexity of this method is O(1)
     */
    public void addToData (String word){
        if (word.length() == 0)                           // If the content of the word is empty do nothing
            return;
        WordNode newString = new WordNode (word);          // Create a node of the word
        if (empty() || this._head.getWord().length() == 0 ){        // If the list is empty then the word will be the head
            _head = newString;
            return;
        }

        if(word.compareTo(_head.getWord()) < 1 ){             // We will check if the word is not in alphabetical order after the first word
            if (word.compareTo(_head.getWord()) == 0 ){      //We will check if it is the same word, if so we will add counter of the word 1
                _head.setCount(_head.getCount() + 1);
                return;
            }
            else{
                WordNode temp = _head;  //If the word is in front of the head we will make it first
                _head = newString;
                newString.setNext(temp);
                return;
            }            
        }
        addToData (newString , _head.getNext() , _head);      //We will now add the rest of the words using the overloading
    }

    private void addToBeginningList(String s){
        WordNode temp = _head;
        WordNode start = new WordNode(s , temp);
        this._head = start;
    }

    /**
     * The time complexity of this method is O(n^2)
     * The space complexity of this method is O(1)
     */
    private void addToData (WordNode temp, WordNode curr , WordNode pre){        // An Overloading of the addToData method.
        while (curr != null){                          // As long as the word comes we are not empty, we will add the word before it.
            if(temp.getWord().compareTo(curr.getWord()) < 1 ){          // We will check if the word is not in alphabetical order after the current word
                if (temp.getWord().compareTo(curr.getWord()) == 0 ){      //We will check if it is the same word, if so we will add counter of the word 1
                    curr.setCount(curr.getCount() + 1);
                    return;
                }
                temp.setNext(curr);          // If the word has a lower alphabetical value then we add the word before the current word
                pre.setNext(temp);
                return;
            }
            curr = curr.getNext();                    //  If it is not before or identical then we will proceed with the words in the list
            pre = pre.getNext();
        }
        pre.setNext(temp);            // If it was not before or identical to all the words in the list then we will add the word as last
    }

    /**
     * Returns the total number of words in the text
     * @return The total number of words in the text
     * The time complexity of this method is O(n)
     * The space complexity of this method is O(1)
     */
    public int howManyWords(){
        if (empty() || this._head.getWord().length() == 0){         // Checks if the list is empty
            return 0;
        }
        int count = 0;
        for (WordNode p = _head ; p != null ; p = p.getNext()){         //Count the number of words and the number of occurrences of each word
            count += p.getCount();
        }
        return count;
    }

    /**
     * Returns the number of different words in the text
     * @return The number of different words in the text
     * The time complexity of this method is O(n)
     * The space complexity of this method is O(1)
     */
    public int howManyDifferentWords(){
        if (empty() || this._head.getWord().length() == 0){        //Checks if the list is empty
            return 0;
        }
        int count = 0;
        for (WordNode p = _head ; p != null ; p = p.getNext()){      //Count the number of words without repetition
            count ++;
        }
        return count;
    }

    /**
     * Returns the most common word in the text. If there is more than one such word, it will be return The first among them
     * @return The most common word in the text. If there is more than one such word, it will be return The first among them
     * The time complexity of this method is O(n)
     * The space complexity of this method is O(1)
     */
    public String mostFrequentWord(){
        if(empty() || this._head.getWord().length() == 0)               //Checks if the list is empty
            return "";
        int max = 0;
        WordNode biggest = new WordNode();             //Creates a new node that maintains the node with the maximum number of instances
        for (WordNode p = _head ; p != null ; p = p.getNext()){
            if ( p.getCount() > max){                 // If the number of occurrences is greater than the current maximum then you will copy the node to the maximum
                biggest = p;
                max = p.getCount();
            }
        }
        return biggest.getWord();                   //If the number of occurrences is greater than the current maximum then you will copy the word to the maximum
    }

    /**
     * Receives a letter, and returns the number of words in the text that begin with that letter
     * @param x The letter that checks how many words in the statring with her
     * @return The number of words in the text that begin with the given letter
     * The time complexity of this method is O(n)
     * The space complexity of this method is O(1)
     */
    public int howManyStarting (char letter){
        if (empty() || this._head.getWord().length() == 0){                    //Checks if the list is empty
            return 0;
        }
        int counter = 0;
        for (WordNode p = _head ; p != null ; p = p.getNext()){       // Goes through all the words and checks if their first letter is the same as the given letter
            if(p.getWord().charAt(0) == letter)
                counter += p.getCount();
        }
        return counter;
    }

    /**
     * Returns the letter that most words begin with in the text. If there are more than one, it will be return The first among them
     * @return The letter that most words begin with in the text. If there are more than one, it will be return The first among them
     * The time complexity of this method is O(n^2)
     * The space complexity of this method is O(1)
     */
    public char mostFrequentStartingLetter(){
        if (empty() || this._head.getWord().length() == 0){
            return ' ';
        }
        WordNode p = _head;
        int [] arr = new int [26];     //Creates an array of all the letters in English
        return mostFrequentStartingLetter(p , arr);             //Uses the overloading method of this method
    }

    /**
     * The time complexity of this method is O(n^2)
     * The space complexity of this method is O(1)
     */
    private char mostFrequentStartingLetter(WordNode p , int [] arr ){       // An Overloading of the mostFrequentStartingLetter method.
        if (p.getNext() == null){     //  Stop the recursion when the next node of the current one is empty
            arr [p.getWord().charAt(0) - 97] += p.getCount();    //  Add the number of occurrences of the word to the cell with the number of the letter where the word begins
            return p.getWord().charAt(0);
        }
        arr [p.getWord().charAt(0) -97] += p.getCount();           // Add in the cell a number according to the position of the word in alphabetical order
        char c = mostFrequentStartingLetter(p.getNext() , arr);      // Creates a variable which holds the cell of the current signal inside it
        if(arr [ p.getWord().charAt(0) - 97] >= arr [c - 97])          //If the maximum that was up to now is greater then return the cell of the maximum
            c = p.getWord().charAt(0);
        return c;                                       //If the cell of the letter of the current word is greater than the maximum then it becomes the maximum
    }

    /**
     * Returns string most common word in the text. If there is more than one such word, it will be return The first among them
     * @return The most common word in the text. If there is more than one such word, it will be return The first among them
     * The time complexity of this method is O(n)
     * The space complexity of this method is O(1)
     */
    public String toString (){             //Passes the list to a string, where all the words appear in it next to each word appears the number of its occurrences in the text
        if (empty() || this._head.getWord().length() == 0){
            return "";
        }
        String res = "";
        for (WordNode p = _head ; p != null ; p = p.getNext()){
            res += p.getWord() + "\t" + p.getCount() + "\n";
        }
        return res;
    }

    /**
     * The time complexity of this method is O(1)
     * The space complexity of this method is O(1)
     */
    private boolean empty() {
        return _head == null;   
    }

    /**
     * The time complexity of this method is O(nlogn)
     * The space complexity of this method is O(1)
     */
    private WordNode mergeSort (WordNode node) {              // Sort Merge Recursion uses to re-separate the list into two lists and then merge you so that they are sorted
        if (node == null || node.getNext() == null)             //Checks whether the list is empty or contains one node
            return node;
        WordNode list2 = split(node);         //Splits into two lists

        node = mergeSort (node);                  // Performs merge sorting also on the splited lists
        list2 = mergeSort (list2);                // Performs merge sorting also on the splited lists
        return merge (node , list2);              // Merge the lists each time so that they are sorted
    }

    /**
     * The time complexity of this method is O(n)
     * The space complexity of this method is O(1)
     */
    private WordNode merge (WordNode list1 , WordNode list2) {              //merge sorted lists in a way that will create one sorted list
        if (list1 == null) 
            return list2;
        if (list2 == null) 
            return list1;

        if (list1.getWord().compareTo(list2.getWord()) < 1) {         //Checks which of the first words of the lists is first in the lexicographic order
            if (list1.getWord().compareTo(list2.getWord()) == 0){        //Is the word the same we will add the number of occurrences of both
                list1.setCount (list1.getCount() + list2.getCount());
                list1.setNext (merge (list1.getNext() , list2.getNext()));    // If the first word in one of the lists appears first in a lexicographic order then we will put it in the next line, we will remove from the list and continue the merge
                return list1;                       //Returns a sorted list
            }

            else {
                list1.setNext (merge (list1.getNext() , list2));
                return list1;                           //Returns a sorted list
            }
        }
        list2.setNext (merge (list1, list2.getNext()));
        return list2;                                 //Returns a sorted list
    }

    /**
     * The time complexity of this method is O(logn)
     * The space complexity of this method is O(n)
     */
    private WordNode split (WordNode node) {            // Split a list into two lists
        if (node == null || node.getNext() == null)
            return null;
        WordNode list2 = node.getNext();
        node.setNext (list2.getNext());
        list2.setNext( split (list2.getNext()));
        return list2;
    }
}