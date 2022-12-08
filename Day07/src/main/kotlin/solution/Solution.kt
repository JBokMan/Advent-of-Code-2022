package solution

import java.io.File

fun main() {
    // read input
    val input = mutableListOf<String>()
    File("src/main/kotlin/solution/Input").forEachLine { input.add(it) }

    val allFolders = mutableSetOf<Folder>()
    val rootFolder = Folder("/", "/")
    allFolders.add(rootFolder)
    var parentFolder = rootFolder
    var currentFolder = rootFolder
    input.forEach { line ->
        val parts = line.split(' ')
        if (parts[0] == "$") {
            if (parts[1] == "cd") {
                if (parts[2] == "/") {
                    parentFolder = rootFolder
                    currentFolder = rootFolder
                } else if (parts[2] == "..") {
                    currentFolder = parentFolder
                    parentFolder = rootFolder.getFolderByPath(parentFolder.path.substringBeforeLast("/"))!!
                } else {
                    parentFolder = currentFolder
                    currentFolder = currentFolder.getFolder(parts[2])!!
                }
            }
        } else if (parts[0] == "dir") {
            if (!currentFolder.hasDir(parts[1])) {
                allFolders.add(currentFolder.addDir(parts[1])!!)
            }
        } else {
            currentFolder.addFile(parts[0].toInt(), parts[1])
        }
        parentFolder.updateFolder(currentFolder)
    }

    val sizes = mutableListOf<Int>()
    allFolders.forEach { folder -> sizes.add(folder.getFolderSize()) }
    val filteredSizes = sizes.filter { size -> size <= 100000 }
    println("part 1 solution: ${filteredSizes.sum()}")

    // part 2
    val diskAvailable = 70000000
    val diskNeeded = 30000000
    val diskFilled = sizes.max()
    val unusedSpace = diskAvailable - diskFilled
    val spaceNeeded = diskNeeded - unusedSpace
    val filteredSizesPart2 = sizes.filter { size -> size >= spaceNeeded }
    println("part 2 solution: ${filteredSizesPart2.min()}")
}

class Folder(val path: String, private val name: String) {
    private val folders = HashMap<String, Folder>()
    private val files = HashMap<String, Int>()

    fun getFolderByPath(path: String): Folder? {
        if (path == "/" || path == "") {
            return this
        }
        val nextName = path.substringAfter("/").substringBefore("/")
        val nextFolder = this.folders[nextName]
        var nextPath = path.substringAfter(nextName)
        if (nextPath == "") {
            nextPath = "/"
        }
        return nextFolder!!.getFolderByPath(nextPath)
    }

    fun getFolder(name: String): Folder? {
        return folders[name]
    }

    fun hasDir(name: String): Boolean {
        return folders[name] != null
    }

    fun addDir(name: String): Folder? {
        val newPath: String = if (this.path == "/") {
            "/$name"
        } else {
            this.path + "/" + name
        }
        folders[name] = Folder(path = newPath, name = name)

        return folders[name]
    }

    fun addFile(size: Int, name: String) {
        files[name] = size
    }

    fun updateFolder(updatedFolder: Folder) {
        if (this == updatedFolder) {
            return
        }
        folders[updatedFolder.name] = updatedFolder
    }

    fun getFolderSize(): Int {
        var size = 0
        val fileSize = files.values.sum()
        size += fileSize
        var folderSize = 0
        folders.forEach { folder ->
            folderSize += folder.value.getFolderSize()
        }
        size += folderSize
        return size
    }

    override fun toString(): String {
        return "name: $name\npath: $path\n" + "files: ${
            files.map { file -> "name: ${file.key} size: ${file.value}" }.joinToString()
        }\n" + "folders: ${folders.map { folder -> "name: ${folder.key}" }.joinToString()}\n"
    }
}