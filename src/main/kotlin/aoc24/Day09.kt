package aoc24

import kotlin.math.min

object Day09 {

    data class Block(val id: Int, val size: Int, val freeAfter: Int)

    fun calculateSolutionOne(input: String): Long {
        val blocks = parseInput(input)
        val compact = compact(blocks)
        return checksum(compact)
    }

    fun calculateSolutionTwo(input: String): Long {
        val blocks = parseInput(input)
        val compact = fastCompact(blocks)
        return checksum(compact)
    }

    private tailrec fun compact(input: List<Block>): List<Block> {
        val nextBlockWithFreeIndex = input.indexOfFirst { it.freeAfter > 0 }
        if (nextBlockWithFreeIndex == -1) return input
        val nextBlockWithFree = input[nextBlockWithFreeIndex]
        val lastBlock = input.last()
        val numberOfBlocksToCompact = min(nextBlockWithFree.freeAfter, lastBlock.size)
        val newBlockWithoutFree = nextBlockWithFree.copy(freeAfter = 0)
        val newBlockToAdd =
            Block(lastBlock.id, numberOfBlocksToCompact, nextBlockWithFree.freeAfter - numberOfBlocksToCompact)
        val newLastBlock = lastBlock.copy(size = lastBlock.size - numberOfBlocksToCompact, freeAfter = 0)
        val newListStart = input.subList(0, nextBlockWithFreeIndex) + newBlockWithoutFree + newBlockToAdd
        val newList = if (newLastBlock.size > 0) {
            newListStart + input.subList(nextBlockWithFreeIndex + 1, input.size - 1) + newLastBlock
        } else {
            val secondToLastBlock = input[input.size - 2].copy(freeAfter = 0)
            newListStart + input.subList(nextBlockWithFreeIndex + 1, input.size - 2) + secondToLastBlock
        }
        return compact(newList)
    }

    private tailrec fun fastCompact(input: List<Block>, index: Int = input.size - 1): List<Block> {
        if (index == 0) return input
        val blockToMove = input[index]
        val nextBlockIndex = input.subList(0, index)
            .indexOfFirst { it.freeAfter >= blockToMove.size }
        if (nextBlockIndex == -1) return fastCompact(input, index - 1)
        val nextBlock = input[nextBlockIndex]
        val modifiedNextBlock = nextBlock.copy(freeAfter = 0)
        val movedBlock = blockToMove.copy(freeAfter = nextBlock.freeAfter - blockToMove.size)
        val newListStart = input.subList(0, nextBlockIndex) + modifiedNextBlock + movedBlock + input.subList(nextBlockIndex + 1, index)
        val adjustedStartFree = newListStart.let {
            val last = it.last()
            val newFree = last.freeAfter + blockToMove.size + blockToMove.freeAfter
            val updatedLast = last.copy(freeAfter = newFree)
            it.dropLast(1) + updatedLast
        }
        val newInput = adjustedStartFree + input.subList(index + 1, input.size)
        return fastCompact(newInput, index )
    }

    private fun checksum(input: List<Block>): Long {
        val digits = input.flatMap { block ->
            (0..<block.size).map { block.id } + (0..<block.freeAfter).map { 0 }
        }
        val idsMulti = digits.mapIndexed { index, id -> index.toLong() * id.toLong() }
        return idsMulti.sum()
    }

    private fun parseInput(input: String): List<Block> =
        input.trim().windowed(2, step = 2, partialWindows = true).mapIndexed { index, pair ->
            if (pair.length == 2) {
                Block(index, pair.first().digitToInt(), pair.last().digitToInt())
            } else {
                Block(index, pair.first().digitToInt(), 0)
            }
        }
}
