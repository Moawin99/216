class BinaryTree():
    def __init__(self, value = None):
        self.data = value
        self.left = None
        self.right = None

    @property
    def data(self):
        return self.__data

    @data.setter
    def data(self, data):
        self.__data = data

    def __iter__(self):
        return self.preorder(self)

    def preorder(self, node):
        if node:
            yield node
            yield from self.preorder(node.left)
            yield from self.preorder(node.right)
            


    def add_leftchild(self, tree):
        if(type(self.data) != type(tree.data)):
            raise TypeError(f"Type mismatch between {type(self.data).__name__} and {type(tree.data).__name__}")
        self.left = tree

    def add_rightchild(self, tree):
        if(type(self.data) != type(tree.data)):
            raise TypeError(f"Type mismatch between {type(self.data)} and {type(tree.data)}")
        self.right = tree

t1 = BinaryTree(0)
t2 = BinaryTree(3)
t2.add_leftchild(BinaryTree(5))
t2.add_rightchild(BinaryTree(4))
t3 = BinaryTree(2)
t3.add_leftchild(BinaryTree(1))
t1.add_leftchild(t2)
t1.add_rightchild(t3)
for node in t1:
    print(node.data)