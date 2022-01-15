package spiral.bit.dev.imageloader.ui.common.extensions

import androidx.paging.PagingSource

fun <T : Any> List<T>.toLoadResult(position: Int, initialPageIndex: Int) =
    PagingSource.LoadResult.Page(
        data = this,
        prevKey = if (position == initialPageIndex) null else position.dec(),
        nextKey = if (this.isEmpty()) null else position.inc()
    )