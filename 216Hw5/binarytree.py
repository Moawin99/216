class BinaryTree():
    def __init__(self):
        self.data = None
        self.left = None
        self.right = None

    def __init__(self, value):
        self.data = value
        self.left = None
        self.right = None


    def add_leftchild(self, (BinaryTree) tree):
        if(!isinstance(tree.data, self.data)):
            raise TypeError()
        self.left = tree

    def add_rightchild(self, (BinaryTree) tree):
        if(!isinstance(tree.data, self.data)):
            raise TypeError()
        self.right = tree