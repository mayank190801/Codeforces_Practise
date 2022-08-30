//some updates in import stuff
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;
import java.util.*;
import java.io.*;
import java.math.*;

//key points learned
//max space ever that could be alloted in a program to pass in cf 
//int[][] prefixSum = new int[201][200_005];   -> not a single array more!!! 
//never allocate memory again again to such bigg array, it will give memory exceeded for sure
//believe in your fucking solution and keep improving it!!! (sometimes)

///common mistakes
// didn't read the question properly

public class bullshit{

    static int mod = (int) (Math.pow(10, 9)+7);
    static final int dx[] = { -1, 0, 1, 0 }, dy[] = { 0, -1, 0, 1 };
    static final int[] dx8 = { -1, -1, -1, 0, 0, 1, 1, 1 }, dy8 = { -1, 0, 1, -1, 1, -1, 0, 1 };
    static final int[] dx9 = { -1, -1, -1, 0, 0, 0, 1, 1, 1 }, dy9 = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };

    static final double eps = 1e-10;
    static List<Integer> primeNumbers = new ArrayList<>();
    public static void main(String[] args) {
        MyScanner sc = new MyScanner();  //pretty important for sure - 
        out = new PrintWriter(new BufferedOutputStream(System.out));  //dope shit output for sure

        //code here
        int test = sc.nextInt();
        while(test --> 0){
            int n = sc.nextInt();
            ArrayList<String> data = new ArrayList<>();
            for(int i= 0; i < 2 * n + 1; i++){
                data.add(sc.nextLine());
            }
            int[] count = new int[26];
            for(String curr : data){
                for(char ch : curr.toCharArray()){
                    count[ch - 'a']++;
                }
            }

            int ans = 0;
            for(int i=0; i < 26; i++){
                if(count[i] > 0 && count[i] % 2 == 1){
                    ans = i;
                    break;
                }
            }

            out.println((char)('a' + ans));
        }





        out.close();
    }

    //new stuff to learn (whenever this is need for them, then only)
    //Lazy Segment Trees
    //Persistent Segment Trees
    //Square Root Decomposition
    //Geometry & Convex Hull
    //High Level DP -- yk yk
    //String Matching Algorithms
    //Heavy light Decomposition

    //Updation Required
    //Fenwick Tree - both are done (sum) 
    //Segment Tree - both are done (min, max, sum)

    //-----CURRENTLY PRESENT-------//
    //Graph
    //DSU
    //powerMODe
    //power
    //Segment Tree (work on this one) 
    //Prime Sieve
    //Count Divisors
    //Next Permutation 
    //Get NCR 
    //isVowel
    //Sort (int)
    //Sort (long)
    //Binomial Coefficient
    //Pair
    //Triplet
    //lcm (int & long)
    //gcd (int & long)
    //gcd (for binomial coefficient)
    //swap (int & char)
    //reverse
    //primeExponentCounts

    //Fast input and output 

    //-------------------------------------------------------------------
    //-------------------------------------------------------------------
    //-------------------------------------------------------------------
    //-------------------------------------------------------------------
    //-------------------------------------------------------------------

    //GRAPH (basic structure)
    public static class Graph{
        public int V;
        public ArrayList<ArrayList<Integer>> edges;

        //2 -> [0,1,2] (current)
        Graph(int V){
            this.V  = V;
            edges = new ArrayList<>(V+1);
            for(int i= 0; i <= V; i++){
                edges.add(new ArrayList<>());
            }
        }

        public void addEdge(int from , int to){
            edges.get(from).add(to);
            edges.get(to).add(from);
        }
    }

    //DSU (path and rank optimised)
    public static class DisjointUnionSets {
        int[] rank, parent;
        int n;

        public DisjointUnionSets(int n)
        {
            rank = new int[n];
            parent = new int[n];
            Arrays.fill(rank, 1);
            Arrays.fill(parent,-1);
            this.n = n;
        }

        public int find(int curr){
            if(parent[curr] == -1)
                return curr;

            //path compression optimisation
            return parent[curr] = find(parent[curr]);
        }

        public void union(int a, int b){
            int s1 = find(a);
            int s2 = find(b);

            if(s1 != s2){
                //union by size
                if(rank[s1] < rank[s2]){
                    parent[s1] = s2;
                    rank[s2] += rank[s1];
                }else{
                    parent[s2] = s1;
                    rank[s1] += rank[s2];
                }
            }
        }
    }

    //with mod
    public static long powerMOD(long x, long y)
    {
        long res = 1L;
        while (y > 0)
        {
            // If y is odd, multiply x with result
            if ((y & 1) != 0){
                x %= mod;
                res %= mod;
                res = (res * x)%mod;
            }
            // y must be even now
            y = y >> 1; // y = y/2
            x%= mod;
            x = (x * x)%mod;  // Change x to x^2
        }
        return res%mod;
    }

    //without mod
    public static long power(long x, long y)
    {
        long res = 1L;
        while (y > 0)
        {
            // If y is odd, multiply x with result
            if ((y & 1) != 0){
                res = (res * x);
            }
            // y must be even now
            y = y >> 1; // y = y/
            x = (x * x);
        }
        return res;
    }

    public static class segmentTree{

        //so let's make a constructor function for this bad boi for sure!!!
        public long[] arr;
        public long[] tree;

        //COMPLEXITY (normal segment tree, stuff)
        //build -> O(n)
        //query -> O(logn)
        //update -> O(logn)
        //update-range -> O(n) (worst case) 

        //simple iteration and stuff for sure
        public segmentTree(long[] arr){
            int n = arr.length;
            this.arr = new long[n];
            for(int i= 0; i < n; i++){
                this.arr[i] = arr[i];
            }

            tree = new long[4*n + 1];
        }

        //pretty basic idea if you read the code once
        //first make child node once
        //then form the parent node using them
        public void buildTree(int s, int e, int index){
            if(s == e){
                tree[index] = arr[s];
                return;
            }

            //recursive case
            int mid = (s + e)/2;
            buildTree(s, mid, 2 * index);
            buildTree(mid + 1, e, 2*index + 1);

            //the condition we want from children be like this
            tree[index] = min(tree[2 * index], tree[2 * index + 1]);
            return;
        }

        //definitely index based 0 query!!! 
        //only int index = 1!!
        //baaki everything is simple as fuck
        public long query(int s, int e, int qs , int qe, int index){
            //complete overlap
            if(s >= qs && e <= qe){
                return tree[index];
            }

            //no overlap
            if(qe < s || qs > e){
                return Long.MAX_VALUE;
            }

            //partial overlap
            int mid = (s + e)/2;
            long left = query( s, mid , qs, qe, 2*index);
            long right = query( mid + 1, e, qs, qe, 2*index + 1);

            return min(left, right);
        }



        //gonna do range updates for sure now!!
        //let's do this bois!!! (solve this problem for sure)
        public void updateRange(int s, int e, int l, int r, long increment, int index){

            //out of bounds
            if(l > e || r < s){
                return;
            }

            //leaf node
            if(s == e){
                tree[index] += increment;
                return;  //behnchoda return tera baap krvayege?
            }

            //recursive case
            int mid = (s + e)/2;
            updateRange(s, mid, l, r, increment, 2 * index);
            updateRange(mid + 1, e, l, r, increment, 2 * index + 1);

            tree[index] = min(tree[2 * index], tree[2 * index + 1]);
        }

    }

    public static class segmentTreeLazy{

        //so let's make a constructor function for this bad boi for sure!!!
        public long[] arr;
        public long[] tree;
        public long[] lazy;

        //COMPLEXITY (normal segment tree, stuff)
        //build -> O(n)
        //query-range -> O(logn)
        //lazy update-range -> O(logn) (imp) 

        //simple iteration and stuff for sure
        public segmentTreeLazy(long[] arr){
            int n = arr.length;
            this.arr = new long[n];
            for(int i= 0; i < n; i++){
                this.arr[i] = arr[i];
            }

            tree = new long[4*n + 1];
            lazy = new long[1000000];  //pretty big for no inconvenience (no?) NONONONOONON! NO fucker NO!
        }

        //pretty basic idea if you read the code once
        //first make child node once
        //then form the parent node using them
        public void buildTree(int s, int e, int index){
            if(s == e){
                tree[index] = arr[s];
                return;
            }

            //recursive case
            int mid = (s + e)/2;
            buildTree(s, mid, 2 * index);
            buildTree(mid + 1, e, 2*index + 1);

            //the condition we want from children be like this
            tree[index] = min(tree[2 * index], tree[2 * index + 1]);
            return;
        }

        //definitely index based 0 query!!! 
        //only int index = 1!! 
        //baaki everything is simple as fuck
        public long queryLazy(int s, int e, int qs, int qe, int index){
            //before going down resolve if it exist
            if(lazy[index] != 0){
                tree[index] += lazy[index];
                //non leaf node
                if(s != e){
                    lazy[2*index] += lazy[index];
                    lazy[2*index + 1] += lazy[index];
                }

                lazy[index] = 0; //clear the lazy value at current node for sure
            }

            //no overlap
            if(s > qe || e < qs){
                return Long.MAX_VALUE;
            }

            //complete overlap
            if(s >= qs && e <= qe){
                return tree[index];
            }

            //partial overlap
            int mid = (s + e)/2;
            long left = queryLazy(s, mid, qs, qe, 2 * index);
            long right = queryLazy(mid + 1, e, qs, qe, 2 * index + 1);

            return Math.min(left, right);
        }

        //update range in O(logn) -- using lazy array
        public void updateRangeLazy(int s, int e, int l, int r, int inc, int index){
            //before going down resolve if it exist
            if(lazy[index] != 0){
                tree[index] += lazy[index];
                //non leaf node
                if(s != e){
                    lazy[2*index] += lazy[index];
                    lazy[2*index + 1] += lazy[index];
                }

                lazy[index] = 0; //clear the lazy value at current node for sure
            }

            //no overlap
            if(s > r || l > e){
                return;
            }

            //another case
            if(l <= s && e <= r){
                tree[index] += inc;

                //create a new lazy value for children node
                if(s != e){
                    lazy[2*index] += inc;
                    lazy[2*index + 1] += inc;
                }
                return;
            }


            //recursive case
            int mid = (s + e)/2;
            updateRangeLazy(s, mid, l, r, inc, 2*index);
            updateRangeLazy(mid + 1, e, l, r, inc, 2*index + 1);

            //update the tree index
            tree[index] = Math.min(tree[2*index], tree[2*index + 1]);
            return;
        }

    }

    //prime sieve
    public static void primeSieve(int n){
        BitSet bitset = new BitSet(n+1);
        for(long i = 0; i < n ; i++){
            if (i == 0 || i == 1) {
                bitset.set((int) i);
                continue;
            }
            if(bitset.get((int) i)) continue;
            primeNumbers.add((int)i);
            for(long j = i; j <= n ; j+= i)
                bitset.set((int)j);
        }
    }

    //number of divisors
    public static int countDivisors(long number){
        if(number == 1) return 1;
        List<Integer> primeFactors = new ArrayList<>();
        int index = 0;
        long curr = primeNumbers.get(index);
        while(curr * curr <= number){
            while(number % curr == 0){
                number = number/curr;
                primeFactors.add((int) curr);
            }
            index++;
            curr = primeNumbers.get(index);
        }

        if(number != 1) primeFactors.add((int) number);
        int current = primeFactors.get(0);
        int totalDivisors = 1;
        int currentCount = 2;
        for (int i = 1; i < primeFactors.size(); i++) {
            if (primeFactors.get(i) == current) {
                currentCount++;
            } else {
                totalDivisors *= currentCount;
                currentCount = 2;
                current = primeFactors.get(i);
            }
        }
        totalDivisors *= currentCount;
        return totalDivisors;
    }

    //primeExponentCounts
    public static int primeExponentsCount(int n) {
        if (n <= 1)
            return 0;
        int sqrt = (int) Math.sqrt(n);
        int remainingNumber = n;
        int result = 0;
        for (int i = 2; i <= sqrt; i++) {
            while (remainingNumber % i == 0) {
                result++;
                remainingNumber /= i;
            }
        }
        //in case of prime numbers this would happen
        if (remainingNumber > 1) {
            result++;
        }
        return result;
    }


    //now adding next permutation function to java hehe
    public static boolean next_permutation(int[] p) {
        for (int a = p.length - 2; a >= 0; --a)
            if (p[a] < p[a + 1])
                for (int b = p.length - 1;; --b)
                    if (p[b] > p[a]) {
                        int t = p[a];
                        p[a] = p[b];
                        p[b] = t;
                        for (++a, b = p.length - 1; a < b; ++a, --b) {
                            t = p[a];
                            p[a] = p[b];
                            p[b] = t;
                        }
                        return true;
                    }
        return false;
    }

    //finding the value of NCR in O(RlogN) time and O(1) space
    public static long getNcR(int n, int r)
    {
        long p = 1, k = 1;
        if (n - r < r) r = n - r;

        if (r != 0) {
            while (r > 0) {
                p *= n;
                k *= r;
                long m = __gcd(p, k);
                p /= m;
                k /= m;
                n--;
                r--;
            }
        }
        else {
            p = 1;
        }
        return p;
    }

    //is vowel function 
    public static boolean isVowel(char c)
    {
        return (c=='a' || c=='A' || c=='e' || c=='E' || c=='i' || c=='I' || c=='o' || c=='O' ||     c=='u' || c=='U');
    }

    //to sort the array with better method
    public static void sort(int[] a) {
        ArrayList<Integer> l=new ArrayList<>();
        for (int i:a) l.add(i);
        Collections.sort(l);
        for (int i=0; i<a.length; i++) a[i]=l.get(i);
    }

    //sort long
    public static void sort(long[] a) {
        ArrayList<Long> l=new ArrayList<>();
        for (long i:a) l.add(i);
        Collections.sort(l);
        for (int i=0; i<a.length; i++) a[i]=l.get(i);
    }

    //for calculating binomialCoeff
    public static int binomialCoeff(int n, int k)
    {
        int C[] = new int[k + 1];
        // nC0 is 1
        C[0] = 1;
        for (int i = 1; i <= n; i++) {
            // Compute next row of pascal
            // triangle using the previous row
            for (int j = Math.min(i, k); j > 0; j--)
                C[j] = C[j] + C[j - 1];
        }
        return C[k];
    }

    //Pair with int int 
    public static class Pair{
        public int a;
        public int b;
        public int hashCode;

        Pair(int a , int b){
            this.a = a;
            this.b = b;
            this.hashCode = Objects.hash(a, b);
        }

        @Override
        public String toString(){
            return a + " -> " + b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Pair that = (Pair) o;
            return a == that.a && b == that.b;
        }

        @Override
        public int hashCode() {
            return this.hashCode;
        }
    }

    //Triplet with int int int
    public static class Triplet{

        public int a;
        public int b;
        public int c;

        Triplet(int a , int b, int c){
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public String toString(){
            return a + " -> " + b;
        }
    }

    //Shortcut function
    public static long lcm(long a , long b){
        return a * (b/gcd(a,b));
    }

    //let's make one for calculating lcm basically
    public static int lcm(int a , int b){
        return (a * b)/gcd(a,b);
    }

    //int version for gcd
    public static int gcd(int a, int b){
        if(b == 0)
            return a;

        return gcd(b , a%b);
    }

    //long version for gcd
    public static long gcd(long a, long b){
        if(b == 0)
            return a;

        return gcd(b , a%b);
    }

    //for ncr calculator(ignore this code)
    public static long __gcd(long n1, long n2)
    {
        long gcd = 1;
        for (int i = 1; i <= n1 && i <= n2; ++i) {
            // Checks if i is factor of both integers
            if (n1 % i == 0 && n2 % i == 0) {
                gcd = i;
            }
        }
        return gcd;
    }

    //swapping two elements in an array
    public static void swap(int[] arr, int left , int right){
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    //for char array
    public static void swap(char[] arr, int left , int right){
        char temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    //reversing an array
    public static void reverse(int[] arr){
        int left = 0;
        int right = arr.length-1;

        while(left <= right){
            swap(arr, left,right);
            left++;
            right--;
        }
    }


    public static long expo(long a, long b, long mod) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1L) res = (res * a) % mod;  //think about this one for a second
            a = (a * a) % mod;
            b = b >> 1;
        }
        return res;
    }

    //SOME EXTRA DOPE FUNCTIONS 
    public static long mminvprime(long a, long b) {
        return expo(a, b - 2, b);
    }

    public static long mod_add(long a, long b, long m) {
        a = a % m;
        b = b % m;
        return (((a + b) % m) + m) % m;
    }

    public static long mod_sub(long a, long b, long m) {
        a = a % m;
        b = b % m;
        return (((a - b) % m) + m) % m;
    }

    public static long mod_mul(long a, long b, long m) {
        a = a % m;
        b = b % m;
        return (((a * b) % m) + m) % m;
    }

    public static long mod_div(long a, long b, long m) {
        a = a % m;
        b = b % m;
        return (mod_mul(a, mminvprime(b, m), m) + m) % m;
    }

    //O(n) every single time remember that
    public static long nCr(long N, long K , long mod){
        long upper = 1L;
        long lower = 1L;
        long lowerr = 1L;

        for(long i = 1; i <= N; i++){
            upper = mod_mul(upper, i, mod);
        }

        for(long i = 1; i <= K; i++){
            lower = mod_mul(lower, i, mod);
        }

        for(long i = 1; i <= (N - K); i++){
            lowerr = mod_mul(lowerr, i, mod);
        }

        // out.println(upper + " " + lower + " " + lowerr);
        long answer = mod_mul(lower, lowerr, mod);
        answer = mod_div(upper, answer, mod);

        return answer;
    }

    // long[] fact = new long[2 * n + 1];
    // long[] ifact = new long[2 * n + 1];
    // fact[0] = 1;
    // ifact[0] = 1;
    // for (long i = 1; i <= 2 * n; i++)
    // {
    //     fact[(int)i] = mod_mul(fact[(int)i - 1], i, mod);
    //     ifact[(int)i] = mminvprime(fact[(int)i], mod);
    // }
    //ifact is basically inverse factorial in here!!!!!(imp)
    public static long combination(long n, long r, long m, long[] fact, long[] ifact) {
        long val1 = fact[(int)n];
        long val2 = ifact[(int)(n - r)];
        long val3 = ifact[(int)r];
        return (((val1 * val2) % m) * val3) % m;
    }




    //-----------PrintWriter for faster output---------------------------------
    public static PrintWriter out;

    //-----------MyScanner class for faster input----------
    public static class MyScanner {
        BufferedReader br;
        StringTokenizer st;

        public MyScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine(){
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

    }
    //--------------------------------------------------------
}