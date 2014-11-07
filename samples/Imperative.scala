def intArrayCopy(src: Array[Int]): Array[Int] = {
    var dest: Array[Int] = new Array[Int](src.length)
    var i = 0
    while (i < src.length) {
        dest(i) = src(i)
        i = i + 1
    }
    dest
}
