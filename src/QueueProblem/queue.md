补充下循环队列相关内容:

1.如果判断空和满状态?
2.队列长度如何计算?

Q1:
    方法一:设一个flag标志位,通过这个来进行判断.
    方法二:空一个位置出来,然后空是`front==read`,满是`(rear+1)/qSize==front`.
Q2:
    对于方法二,队列长度`len=(rear-front+qSize)%qSize`,其中qSize是整个数组长度(包含空的那个位置).

对于这些基础性的东西,选择性记忆推导下即可.