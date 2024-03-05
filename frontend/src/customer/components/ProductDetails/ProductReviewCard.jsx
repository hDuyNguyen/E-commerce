import { Avatar, Box, Grid, Rating } from '@mui/material'
import React from 'react'

const ProductReviewCard = () => {
    return (
        <div>
            <Grid container spacing={2} gap={3}>
                <Grid item xs={1}>
                    <Box>
                        <Avatar className='text-white' sx={{ width: 56, height: 56, bgcolor: "#9155fd" }}>R</Avatar>
                    </Box>
                </Grid>

                <Grid item xs={9}>
                    <div className='space-y-2'>
                        <div>
                            <p className='font-semibold text-lg'>Duy</p>
                            <p className='opacity-70'>March 2, 2024</p>
                        </div>
                    </div>

                    <Rating value={2.5} name='half-rating' readOnly precision={0.5} />
                    <p>Day la rating</p>
                </Grid>
            </Grid>
        </div>
    )
}

export default ProductReviewCard