# 基本文法

# import

    # import [lib]
    import time


# value

    # None = null

    # [name] = [value]
    value = 1

    # [name]: [type] = [value]
    value: int = 1

    # other type (= on java)
        # str = String
        # int = int
        # float = float
        # bool = boolean
            # warn: boolean=true/false at java, but bool=True/False on Python.
        # datetime = Date
        # list[A] = List<A>
            # val:list[type]
            # val_list:list[int] = []
             # as  int[] val_list =  {};
        # tuple = Pair(?)
        # dictionary = Map<K,V>
            # dic:dictionary, dic[Key]
             # as  Map.get(K);
            # dic:dictionary, dic[newKey] = newValue
             # as  Map.put(K,V);

    # type(val) <- cast
    str_value:str = "1100112"
    int_value:int = int(str_value)

    # warn: can't write const on python (... Wait, what?)


# standard I/O

    # val = input() =
        # Scanner sc = new Scanner(System.in);
        # int val = sc.nextInt();  // sc.next <- String

    # warn: input() <- str

    # print(val) = 
        # System.print.out(val);


# if

    # at python
    if 0==bool:
        # something
    else:
        # something

    # at java
    if (!bool) {
        # something
    } else {
        # something
    }

    # warn: True=1, False=0 at Python.


# for

    # at python
    for i in range(10):
        print(i)

    # at java
    for (int i=1; i<10, i++) {
        System.out.println(i);
    }

    # continue & break  can be used as in Java


# String

    # Use '' or "" for character, It is recognized as a string
    char1:str = 'sample'
    char2:str = "sample"

    # append works as concat(from Java)
    char3:str = None
    char3.append(char1)
    char3.append(char2)
    print(char3)     # [samplesample]


# Calculation

    # add
    1 + 1

    # sub
    2 - 1

    # div
    2 / 2

    # remainder
    5 % 2

    # warn: remainder means *warizan no amari*
    rem: int = 5 % 2   # <- rem=1

    # warn: In python2 and python3, it is divided whether to truncate when dividing or not.
    
        # at python2
        div_py2 = 5 / 2     # <- 2

        # at python3
        div_py3 = 5 / 2     # <- 2.5


# function

    # in python
    def name():
        # something

    # in java
    public void name() {
        # something
    }
    
    # in python
    val:int = 1
    def name(val):
        #something

    #in java
    public void name(int val) {
        #something
    }

    # in python
    def name():
        #something
        return 1

    # in java
    public int name() {
        #something
        return 1;
    }

    # can setting variable defaut value at python
    def default(nullval=5):
        print(nullval)
    default()     # <- 5

    # warn: can't make private function on python (SERIOUS?)

