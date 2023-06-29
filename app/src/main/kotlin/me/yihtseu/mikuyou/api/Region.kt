package me.yihtseu.mikuyou.api

import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Region(
    val name: String = "Popular",
    val tid: Int = 0
) : Parcelable

@Parcelize
data class TopRegion(
    val name: String = "Popular",
    val tid: Int = 0,
    val children: List<Region> = listOf()
) : Parcelable

val topRegions = listOf<TopRegion>(
    TopRegion("Popular", 0, listOf()),
    TopRegion("Douga", 1, listOf(
        Region("MAD", 24),
        Region("MMD", 25),
        Region("Voice", 47),
        Region("Garage", 210),
        Region("Tokusatsu", 86),
        Region("ACGN Talks", 253),
        Region("Other", 27)
    )),
    TopRegion("Anime", 13, listOf(
        Region("Information", 13),
        Region("Offical", 152),
        Region("Finish", 32),
        Region("Serial", 33)
    )),
    TopRegion("Guochuang", 167, listOf(
        Region("Chinese", 153),
        Region("Original", 168),
        Region("Puppetry", 169),
        Region("Information", 170),
        Region("Motioncomic", 195)
    )),
    TopRegion("Music", 3, listOf(
        Region("Original", 28),
        Region("Cover", 31),
        Region("Vocaloid", 30),
        Region("Perform", 59),
        Region("MV", 193),
        Region("Live", 29),
        Region("Other", 130),
        Region("Commentary", 243),
        Region("Tutorial", 244)
    )),
    TopRegion("Dance", 129, listOf(
        Region("Otaku", 20),
        Region("3D", 154),
        Region("Demo", 156),
        Region("Hiphop", 198),
        Region("Star", 199),
        Region("China", 200)
    )),
    TopRegion("Game", 4, listOf(
        Region("Stand", 17),
        Region("E Sports", 171),
        Region("Mobile", 172),
        Region("Online", 65),
        Region("Board", 173),
        Region("GMV", 121),
        Region("Music", 136),
        Region("Mugen", 19)
    )),
    TopRegion("Knowledge", 36, listOf(

    )),
    TopRegion("Tech", 188, listOf(

    )),
    TopRegion("Sports", 234, listOf(

    )),
    TopRegion("Car", 223, listOf(

    )),
    TopRegion("Life", 160, listOf(

    )),
    TopRegion("Food", 211, listOf(

    )),
    TopRegion("Animal", 217, listOf(

    )),
    TopRegion("Kichiku", 119, listOf(

    )),
    TopRegion("Fashion", 155, listOf(

    )),
    TopRegion("Information", 202, listOf(

    )),
    TopRegion("Ent", 5, listOf(

    )),
    TopRegion("Cinephile", 181, listOf(

    )),
    TopRegion("Documentary", 177, listOf(

    )),
    TopRegion("Movie", 23, listOf(

    )),
    TopRegion("TV", 1, listOf(

    )),
)