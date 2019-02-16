public class Palindrome{

    public Deque<Character> wordToDeque(String word){

        Deque<Character> wordDeque = new LinkedListDeque<>();
        for (char ch : word.toCharArray()) {
            wordDeque.addLast(ch);
        }
        return wordDeque;
    }

    public boolean isPalindromeNonRecursion(String word){
        boolean flag = true;
        for(int i = 0; i < word.length()/2; i++){
            if(word.charAt(i) != word.charAt(word.length() - 1 - i)){
                flag = false;
                return flag;
            }else{
                flag = true;
            }
        }
        return flag;
    }

    public boolean isPalindrome(String word){

        Deque<Character> wordDeque = wordToDeque(word);
        String reverse = isPalindrome(wordDeque);
        if(word.equals(reverse)){
            return true;
        }
        return false;


    }
    private String isPalindrome(Deque word){
        if(word.size() == 0){
            return "";
        }
        return (char) word.removeLast() + isPalindrome(word);
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        for (int i = 0; i < word.length() / 2; i++){
            boolean flag = cc.equalChars(word.charAt(i), word.charAt(word.length() - 1 - i));
            if(flag == false){
                return false;
            }
        }
        return true;

    }


}


